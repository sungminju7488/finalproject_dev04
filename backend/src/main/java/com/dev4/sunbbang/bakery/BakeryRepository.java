package com.dev4.sunbbang.bakery;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev4.sunbbang.model.BakeryVO;

public interface BakeryRepository extends JpaRepository<BakeryVO, String>{
	public Optional<BakeryVO> findByMemberSeq(String memberSeq);
	public Optional<Page<BakeryVO>> findByStoreNameContaining(String keyword, Pageable pagealbe);
}
