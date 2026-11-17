package io.neko.workshop.module.member.api.address;

import io.neko.workshop.module.member.api.address.dto.AddressRespDTO;
import io.neko.workshop.module.member.convert.address.AddressConvert;
import io.neko.workshop.module.member.service.address.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * 用户收件地址 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class AddressApiImpl implements AddressApi {

    private final AddressService addressService;

    @Override
    public AddressRespDTO getAddress(Long id, Long userId) {
        return AddressConvert.INSTANCE.convert02(addressService.getAddress(userId, id));
    }

}
