package service;

import java.util.List;

import common.FieldType;
import vo.FieldRankInf;

public interface FieldService {
	public List<FieldRankInf> getHotBlock(FieldType type)throws Exception;
}
