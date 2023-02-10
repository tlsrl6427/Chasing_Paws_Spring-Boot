package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Mop;

import java.util.List;
import java.util.Optional;

public interface MopRepository {

    Optional<Mop> findByStage(int stage_val);

}
