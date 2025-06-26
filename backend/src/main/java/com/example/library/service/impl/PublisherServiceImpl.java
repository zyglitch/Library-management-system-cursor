package com.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library.model.Publisher;
import com.example.library.mapper.PublisherMapper;
import com.example.library.service.PublisherService;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl extends ServiceImpl<PublisherMapper, Publisher> implements PublisherService {
} 