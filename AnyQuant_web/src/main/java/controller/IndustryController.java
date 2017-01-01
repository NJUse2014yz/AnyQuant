package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.FieldType;
import service.FieldService;
import vo.FieldRankInf;

/**
 * 行业
 */
@Controller
public class IndustryController {
	
	@Autowired
	FieldService bl;

	@RequestMapping("/industry")
	public ModelAndView industry() throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("industry/industry");
		return modelAndView;
	}
	
	/**
	 * 获得行业排行榜
	 * 2016年6月8日
	 * @throws Exception 
	 */
	@RequestMapping("/getIndustryRank")
	public @ResponseBody List<FieldRankInf> getIndustryRank() throws Exception{
		List<FieldRankInf> industryRank=bl.getHotBlock(FieldType.Field);
		return industryRank;
	}
	
	/**
	 * 获得地域排行榜
	 * 2016年6月8日
	 * @throws Exception 
	 */
	@RequestMapping("/getAreaRank")
	public @ResponseBody List<FieldRankInf> getAreaRank() throws Exception{
		List<FieldRankInf> areaRank=bl.getHotBlock(FieldType.Area);
		return areaRank;
	}
	
	/**
	 * 获得行业排行榜
	 * 2016年6月8日
	 * @throws Exception 
	 */
	@RequestMapping("/getConceptRank")
	public @ResponseBody List<FieldRankInf> getConceptRank() throws Exception{
		List<FieldRankInf> conceptRank=bl.getHotBlock(FieldType.Concept);
		return conceptRank;
	}
	
	
	
	private List<FieldRankInf> getVirtulList(){
		List<FieldRankInf> industryRank=new ArrayList<>();
		FieldRankInf info1=new FieldRankInf("腾讯游戏", 998, 648.0);
		industryRank.add(info1);
		FieldRankInf info2=new FieldRankInf("搜狐游戏", 800, 328.0);
		industryRank.add(info2);
		FieldRankInf info3=new FieldRankInf("网易游戏", 660, 168.0);
		industryRank.add(info3);
		FieldRankInf info4=new FieldRankInf("新浪游戏", 560, 98.0);
		industryRank.add(info4);
		FieldRankInf info5=new FieldRankInf("盛大游戏", 460, 50.0);
		industryRank.add(info5);
		FieldRankInf info6=new FieldRankInf("百度游戏", 400, 25.0);
		industryRank.add(info6);
		FieldRankInf info7=new FieldRankInf("暴雪游戏", 340, 18.0);
		industryRank.add(info7);
		return industryRank;
	}
	
}
