param(
    [Parameter(Mandatory = $true)][string]$ProjectRoot,
    [Parameter(Mandatory = $true)][string]$OldRoot,
    [switch]$WhatIfOnly
)

$ErrorActionPreference = 'Stop'
$ProjectRoot = (Get-Item -LiteralPath $ProjectRoot).FullName.TrimEnd('\')
$nodeModules = Join-Path $ProjectRoot 'node_modules'
if (-not (Test-Path -LiteralPath $nodeModules)) { throw "node_modules not found: $nodeModules" }
$OldRoot = $OldRoot.TrimEnd('\')

function Get-ReparseDirectories([string]$Start) {
    $queue = New-Object System.Collections.Generic.Queue[string]
    $queue.Enqueue($Start)
    $found = New-Object System.Collections.Generic.List[string]
    while ($queue.Count -gt 0) {
        $dir = $queue.Dequeue()
        try { $children = [System.IO.Directory]::GetDirectories($dir) } catch { continue }
        foreach ($child in $children) {
            $info = New-Object System.IO.DirectoryInfo $child
            if ($info.Attributes -band [System.IO.FileAttributes]::ReparsePoint) {
                $found.Add($child)
            } else {
                $queue.Enqueue($child)
            }
        }
    }
    return $found
}

$all = Get-ReparseDirectories $nodeModules
Write-Output "reparse directories found: $($all.Count)"

$repaired = 0
$alreadyOk = 0
$failed = New-Object System.Collections.Generic.List[string]

foreach ($path in $all) {
    $info = New-Object System.IO.DirectoryInfo $path
    $target = $info.Target
    if ($target -is [array]) { $target = $target[0] }
    if (-not $target) { continue }
    if (Test-Path -LiteralPath $target) { $alreadyOk++; continue }
    if (-not $target.StartsWith($OldRoot, [System.StringComparison]::OrdinalIgnoreCase)) {
        $failed.Add("FOREIGN  $path -> $target")
        continue
    }
    $newTarget = $ProjectRoot + $target.Substring($OldRoot.Length)
    if (-not (Test-Path -LiteralPath $newTarget)) {
        $failed.Add("MISSING  $path -> $newTarget")
        continue
    }
    if ($WhatIfOnly) {
        Write-Output "WOULD $path -> $newTarget"
        $repaired++
        continue
    }
    try {
        [System.IO.Directory]::Delete($path, $false)
        $null = New-Item -ItemType Junction -Path $path -Target $newTarget
        $repaired++
    } catch {
        $failed.Add("ERROR    $path : $($_.Exception.Message)")
    }
}

Write-Output "already ok : $alreadyOk"
Write-Output "repaired   : $repaired"
Write-Output "failed     : $($failed.Count)"
$failed | Select-Object -First 30 | ForEach-Object { Write-Output $_ }

# verify nothing is still broken
$still = 0
foreach ($path in $all) {
    $info = New-Object System.IO.DirectoryInfo $path
    $target = $info.Target
    if ($target -is [array]) { $target = $target[0] }
    if ($target -and -not (Test-Path -LiteralPath $target)) { $still++ }
}
Write-Output "still broken: $still"
