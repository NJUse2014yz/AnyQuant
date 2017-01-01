package service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

import bl.StrateHelper;
import exception.DataLackException;
import net.sf.json.JSONException;
import service.StrategyService;
import vo.StrategyRecInf;
import vo.UserStrategy;
import vo.UserStrategyProperty;

public class StrategyServiceImpl implements StrategyService {

	@Override
	public List<StrategyRecInf> getRecStrategy(String id) throws Exception {
		// TODO Auto-generated method stub
		try {
			File file = new File("E:/python/strategy.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			FileWriter fileWritter = new FileWriter("E:/python/strategy.txt", false);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(id.substring(2));
			bufferWritter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 调用python脚本
		Runtime run = Runtime.getRuntime();
		String result = "";
		try {
			Process p = run.exec("python E:/python/StrategyRec.py");
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = br.readLine()) != null) {
				result += lineStr;
			}
			br.close();
			in.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(result);
		List<StrategyRecInf> relist=null;
		try{
		relist=StrateHelper.turnStrategyRec(result);
		}catch(JSONException e){
			throw new DataLackException();
		}
		return relist;
	}

	@Override
	public UserStrategy createUserStrategy(String UserName, UserStrategyProperty property) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		StrategyServiceImpl here = new StrategyServiceImpl();
		try{
		List<StrategyRecInf> temp = here.getRecStrategy("sh200001");
		}catch(DataLackException e){
			System.out.println("BBBBBBBBBBBB");
		}
	}

}
