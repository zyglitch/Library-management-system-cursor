package com.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library.model.Faculty;
import com.example.library.mapper.FacultyMapper;
import com.example.library.service.FacultyService;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl extends ServiceImpl<FacultyMapper, Faculty> implements FacultyService {
} 