package com.project.cigar.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cigar.mapper.CigarMapperInter;
import com.project.cigar.to.CigarListTO;
import com.project.cigar.to.CigarTO;

@Repository
@MapperScan("com.project.cigar.mapper")
public class CigarDAO {
	
	@Autowired
	private CigarMapperInter cigarMapperInter;
	
	public CigarListTO cigarList(CigarListTO listTO){
		ArrayList<CigarTO> cigarLists = cigarMapperInter.cigarList();
		
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();
		
		listTO.setTotalRecord(cigarLists.size());
		listTO.setTotalPage(((listTO.getTotalRecord() - 1) / recordPerPage) + 1);
		int skip = (cpage - 1) * recordPerPage;
		if(listTO.getTotalRecord() - skip < 10) {
			recordPerPage = listTO.getTotalRecord() - skip;
		}
		
		ArrayList<CigarTO> cigarViewList = new ArrayList<CigarTO>();
		for(int i = 0; i < recordPerPage ; i++) {
			CigarTO to = new CigarTO();
			to.setCigar_seq(cigarLists.get(skip+i).getCigar_seq());
			to.setCigar_writer_seq(cigarLists.get(skip+i).getCigar_writer_seq());
			to.setCigar_brand(cigarLists.get(skip+i).getCigar_brand());
			to.setCigar_name(cigarLists.get(skip+i).getCigar_name());
			to.setCigar_tar(cigarLists.get(skip+i).getCigar_tar());
			to.setCigar_nicotine(cigarLists.get(skip+i).getCigar_nicotine());
			to.setCigar_file_name(cigarLists.get(skip+i).getCigar_file_name());
			to.setCigar_file_size(cigarLists.get(skip+i).getCigar_file_size());
			to.setCigar_hash_tag(cigarLists.get(skip+i).getCigar_hash_tag());
			to.setCigar_content(cigarLists.get(skip+i).getCigar_content());
			to.setCigar_total_grade(cigarLists.get(skip+i).getCigar_total_grade());
			
			cigarViewList.add(to);
		}
		
		listTO.setCigarLists(cigarViewList);
		
		listTO.setStartBlock(cpage - (cpage - 1) % blockPerPage);
		listTO.setEndBlock(cpage - (cpage - 1) % blockPerPage + blockPerPage - 1);
		if (listTO.getEndBlock() >= listTO.getTotalPage()) {
			listTO.setEndBlock(listTO.getTotalPage());
		}
		
		return listTO;
	}
	
	public CigarTO cigarView(CigarTO to) {
		int res = cigarMapperInter.cigarView_hit(to);
		System.out.println("조회수 상승 값 : " + res);
		to = cigarMapperInter.cigarView(to);
		return to;
	}
	
	public void cigarWrite() {
		
	}
	
	public int cigarWriteOk(CigarTO to) {
		int result = cigarMapperInter.cigarWirte_ok(to);
		int flag = 1;
		if(result == 1) {
			flag = 0;
		}
		
		return flag;
	}
	
	public CigarTO cigarModify(CigarTO to) {
		to = cigarMapperInter.cigarModify(to);
		return to;
	}
	
	public int cigarModifyOk(CigarTO to) {
		int result = cigarMapperInter.cigarModify_ok(to);
		int flag = 2;
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
		}
		return flag;
	}
	
	public CigarTO cigarDelete(CigarTO to) {
		to = cigarMapperInter.cigarDelete(to);
		return to;
	}
	
	public int cigarDeleteOk(CigarTO to) {
		int result = cigarMapperInter.cigarDelete_ok(to);
		int flag = 2;
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
		}
		return flag;
	}
}
