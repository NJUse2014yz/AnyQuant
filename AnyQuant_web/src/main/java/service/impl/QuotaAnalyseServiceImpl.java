package service.impl;

import java.sql.Date;

import bl.BiasAnalyse;
import bl.BollAnalyse;
import bl.DmiAnalyse;
import bl.KdjAnalyse;
import bl.MacdAnalyse;
import bl.ObvAnalyse;
import bl.RocAnalyse;
import bl.RsiAnalyse;
import bl.VrAnalyse;
import service.QuotaAnalyseService;
import vo.QuotaAnalysis;

public class QuotaAnalyseServiceImpl implements QuotaAnalyseService {
	
	@Override
	public QuotaAnalysis getBiasAnalysis(String sid) throws Exception {
		return new BiasAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getBiasAnalysis(String sid, Date startdate6,Date startdate12, Date startdate24, 
			double separator6_up, double separator12_up, double separator24_up,
			double separator6_mid, double separator12_mid, double separator24_mid, 
			double separator6_down,double separator12_down, double separator24_down) throws Exception {
		return new BiasAnalyse(sid,startdate6,startdate12,startdate24,
				separator6_up,separator12_up,separator24_up,
				separator6_mid,separator12_mid,separator24_mid,
				separator6_down,separator12_down,separator24_down).pro_quo();
	}

	@Override
	public QuotaAnalysis getRsiAnalysis(String sid) throws Exception {
		return new RsiAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getRsiAnalysis(String sid, Date startdate,double up,double mid,double down, double deviation) throws Exception {
		return new RsiAnalyse(sid, startdate, up, mid, down,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getVrAnalysis(String sid) throws Exception {
		return new VrAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getVrAnalysis(String sid, double in1, double in2,
			double wait1, double wait2, double out1, double out2,
			Date startdate,Date enddate,double deviation)
			throws Exception {
		return new VrAnalyse(sid,in1,in2,wait1,wait2,out1,out2,startdate,enddate,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getObvAnalysis(String sid) throws Exception {
		return new ObvAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getBollAnalysis(String sid) throws Exception {
		return new BollAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getKdjAnalysis(String sid) throws Exception {
		return new KdjAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getMacdAnalysis(String sid) throws Exception {
		return new MacdAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getDmiAnalysis(String sid) throws Exception {
		return new DmiAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getRocAnalysis(String sid) throws Exception {
		return new RocAnalyse(sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getBollAnalysis(String sid, double deviation)
			throws Exception {
		return new BollAnalyse(sid,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getDmiAnalysis(String sid, double deviation)
			throws Exception {
		return new DmiAnalyse(sid,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getMacdAnalysis(String sid, double deviation)
			throws Exception {
		return new MacdAnalyse(sid,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getObvAnalysis(String sid, double obvfastspeed5,
			double obvslowspeed5, double closefastspeed5,
			double closeslowspeed5, double obvfastspeed10,
			double obvslowspeed10, double closefastspeed10,
			double closeslowspeed10, double obvfastspeed20,
			double obvslowspeed20, double closefastspeed20,
			double closeslowspeed20) throws Exception {
		return new ObvAnalyse(sid,  obvfastspeed5,  obvslowspeed5,  closefastspeed5,  closeslowspeed5,
			 obvfastspeed10,  obvslowspeed10,  closefastspeed10,  closeslowspeed10,
			 obvfastspeed20,  obvslowspeed20,  closefastspeed20,  closeslowspeed20).pro_quo();
	}

	@Override
	public QuotaAnalysis getRocAnalysis(String sid, Date startdate,
			double deviation, double up1, double down1) throws Exception {
		return new RocAnalyse(sid,startdate,deviation,up1,down1).pro_quo();
	}

	@Override
	public QuotaAnalysis getBiasAnalysis(String type,String sid) throws Exception {
		return new BiasAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getBiasAnalysis(String type,String sid, Date startdate6,Date startdate12, Date startdate24, 
			double separator6_up, double separator12_up, double separator24_up,
			double separator6_mid, double separator12_mid, double separator24_mid, 
			double separator6_down,double separator12_down, double separator24_down) throws Exception {
		return new BiasAnalyse(type,sid,startdate6,startdate12,startdate24,
				separator6_up,separator12_up,separator24_up,
				separator6_mid,separator12_mid,separator24_mid,
				separator6_down,separator12_down,separator24_down).pro_quo();
	}

	@Override
	public QuotaAnalysis getRsiAnalysis(String type,String sid) throws Exception {
		return new RsiAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getRsiAnalysis(String type,String sid, Date startdate,double up,double mid,double down, double deviation) throws Exception {
		return new RsiAnalyse(type,sid, startdate, up, mid, down,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getVrAnalysis(String type,String sid) throws Exception {
		return new VrAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getVrAnalysis(String type,String sid, double in1, double in2,
			double wait1, double wait2, double out1, double out2,
			Date startdate,Date enddate,double deviation)
			throws Exception {
		return new VrAnalyse(type,sid,in1,in2,wait1,wait2,out1,out2,startdate,enddate,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getObvAnalysis(String type,String sid) throws Exception {
		return new ObvAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getBollAnalysis(String type,String sid) throws Exception {
		return new BollAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getKdjAnalysis(String type,String sid) throws Exception {
		return new KdjAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getMacdAnalysis(String type,String sid) throws Exception {
		return new MacdAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getDmiAnalysis(String type,String sid) throws Exception {
		return new DmiAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getRocAnalysis(String type,String sid) throws Exception {
		return new RocAnalyse(type,sid).pro_quo();
	}

	@Override
	public QuotaAnalysis getBollAnalysis(String type,String sid, double deviation)
			throws Exception {
		return new BollAnalyse(type,sid,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getDmiAnalysis(String type,String sid, double deviation)
			throws Exception {
		return new DmiAnalyse(type,sid,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getMacdAnalysis(String type,String sid, double deviation)
			throws Exception {
		return new MacdAnalyse(type,sid,deviation).pro_quo();
	}

	@Override
	public QuotaAnalysis getObvAnalysis(String type,String sid, double obvfastspeed5,
			double obvslowspeed5, double closefastspeed5,
			double closeslowspeed5, double obvfastspeed10,
			double obvslowspeed10, double closefastspeed10,
			double closeslowspeed10, double obvfastspeed20,
			double obvslowspeed20, double closefastspeed20,
			double closeslowspeed20) throws Exception {
		return new ObvAnalyse(type,sid,  obvfastspeed5,  obvslowspeed5,  closefastspeed5,  closeslowspeed5,
			 obvfastspeed10,  obvslowspeed10,  closefastspeed10,  closeslowspeed10,
			 obvfastspeed20,  obvslowspeed20,  closefastspeed20,  closeslowspeed20).pro_quo();
	}

	@Override
	public QuotaAnalysis getRocAnalysis(String type,String sid, Date startdate,
			double deviation, double up1, double down1) throws Exception {
		return new RocAnalyse(type,sid,startdate,deviation,up1,down1).pro_quo();
	}
}
