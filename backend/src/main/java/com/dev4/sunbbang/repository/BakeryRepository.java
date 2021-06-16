package com.dev4.sunbbang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.MemberVO;

public interface BakeryRepository extends JpaRepository<BakeryVO, String> {

	public Optional<BakeryVO> findByMemberVO(MemberVO memberVO);

	public Optional<Page<BakeryVO>> findByStoreNameContaining(String keyword, Pageable pagealbe);

	@Query(value = "SELECT ABS(LATITUDE - ?1) + ABS(LONGITUDE - ?2) AS DISTANCE, BAKERY.* FROM BAKERY WHERE STORENAME LIKE %?3% ORDER BY DISTANCE", nativeQuery = true)
	public Optional<List<BakeryVO>> searchBakery(String MyLatitude, String MyLongitude, String keyword);

	public void deleteByMemberVO(MemberVO memberVO);
}
