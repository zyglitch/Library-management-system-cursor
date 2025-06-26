package com.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library.model.Administrator;
import com.example.library.mapper.AdministratorMapper;
import com.example.library.service.AdministratorService;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {
} 