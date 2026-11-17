param([Parameter(Mandatory = $true)][string]$ProjectRoot)

$ErrorActionPreference = 'Stop'
$ProjectRoot = (Get-Item -LiteralPath $ProjectRoot).FullName.TrimEnd('\')
$store = Join-Path $ProjectRoot 'node_modules\.pnpm'
$entries = [System.IO.Directory]::GetDirectories($store) | Where-Object { [System.IO.Path]::GetFileName($_) -ne 'node_modules' }

$missing = New-Object System.Collections.Generic.List[string]
$checked = 0

foreach ($entry in $entries) {
    $nm = Join-Path $entry 'node_modules'
    if (-not [System.IO.Directory]::Exists($nm)) { continue }
    # direct children (real dirs only) = the package payload dirs
    foreach ($child in [System.IO.Directory]::GetDirectories($nm)) {
        $name = [System.IO.Path]::GetFileName($child)
        if ($name.StartsWith('.ignored')) { continue }
        $childInfo = New-Object System.IO.DirectoryInfo $child
        if ($childInfo.Attributes -band [System.IO.FileAttributes]::ReparsePoint) { continue }
        if ($name.StartsWith('@')) {
            foreach ($scoped in [System.IO.Directory]::GetDirectories($child)) {
                $scopedInfo = New-Object System.IO.DirectoryInfo $scoped
                if ($scopedInfo.Attributes -band [System.IO.FileAttributes]::ReparsePoint) { continue }
                $checked++
                if (-not [System.IO.File]::Exists((Join-Path $scoped 'package.json'))) {
                    $missing.Add($scoped)
                }
            }
        } else {
            $checked++
            if (-not [System.IO.File]::Exists((Join-Path $child 'package.json'))) {
                $missing.Add($child)
            }
        }
    }
}

Write-Output "payload dirs checked: $checked"
Write-Output "payload dirs missing package.json: $($missing.Count)"
$missing | ForEach-Object { Write-Output ("MISSING " + $_) }
