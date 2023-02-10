package com.chasingpaws.sb.service;

import com.chasingpaws.sb.domain.Mop;
import com.chasingpaws.sb.repository.MopRepository;

public class MopService {

    private final MopRepository mopRepository;

    public MopService(MopRepository mopRepository) {
        this.mopRepository = mopRepository;
    }

    public Mop findOne(int stage_val){
        return mopRepository.findByStage(stage_val).get();
    }
}
