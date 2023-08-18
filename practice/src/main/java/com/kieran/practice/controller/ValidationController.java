package com.kieran.practice.controller;

import com.kieran.practice.domain.vo.response.ApiResult;
import com.kieran.practice.dto.UserDTO;
import com.kieran.practice.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated // 加上此注解才能开启校验功能，并且必须在类上
@Api(tags = "数据验证使用示例接口")
@RequestMapping("validation")
@RestController
public class ValidationController {

    /**
     * redisService
     */
    @Resource
    private RedisService redisService;

    /**
     * Get测试
     * @param name
     * @return
     */
    @GetMapping
    public ApiResult<String> testGet(@NotBlank(message = "不能为空") @RequestParam String name) {
        redisService.addKV(name);
        return ApiResult.success(name);
    }

    /**
     * 校验对象 @Valid @RequestBody两个参数都需要
     */
    @PostMapping
    public ApiResult<UserDTO> testPost(@Valid @RequestBody UserDTO userDTO) {
        return ApiResult.success(userDTO);
    }

}
