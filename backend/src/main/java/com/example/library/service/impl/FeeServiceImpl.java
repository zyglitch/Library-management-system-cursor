package com.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library.model.Fee;
import com.example.library.mapper.FeeMapper;
import com.example.library.service.FeeService;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl extends ServiceImpl<FeeMapper, Fee> implements FeeService {
} 