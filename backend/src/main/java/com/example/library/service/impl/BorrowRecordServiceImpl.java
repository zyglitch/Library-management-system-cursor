package com.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library.model.BorrowRecord;
import com.example.library.mapper.BorrowRecordMapper;
import com.example.library.service.BorrowRecordService;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordServiceImpl extends ServiceImpl<BorrowRecordMapper, BorrowRecord> implements BorrowRecordService {
} 