package service.impl;

import java.util.ArrayList;
import java.util.List;

import bl.DataHelper;
import bl.FieldHelper;
import common.FieldType;
import data.DataService;
import data.impl.DataServiceImpl;
import po.AreaInf;
import po.BlockHistoryData;
import po.ConceptInf;
import po.HistoryData;
import po.IndustryInf;
import service.FieldService;
import vo.FieldRankInf;
import vo.StockListInf;

public class FieldServiceImpl implements FieldService {
	private DataService dataService = new DataServiceImpl();

	@Override
	public List<FieldRankInf> getHotBlock(FieldType type) throws Exception {
		// TODO Auto-generated method stub
		List<String> blocklist = null;
		ArrayList<FieldRankInf> resultlist = new ArrayList<FieldRankInf>();
		switch (type) {
		case Field:
			double[] basenum1 = { 0.15083394845963127, 0.07007802582696722, 0.12096734071414371, 0.17580607365680925,
					0.23714571873694631, 0.11913596253650352, 0.17558766319903005, 0.26356392131095097,
					0.12906477665933766, 0.31335334118917857, 0.1409452929207854, 0.12720256968792842,
					0.1728253367566907, 0.1662023791707299, 0.18070316281106077, 0.3135609986247865, 0.1337322928596877,
					0.13101643882637376, 0.16663913043731138, 0.07470097024297964, 0.14388080008202733,
					0.07967722763529782, 0.16455236494794778, 0.19203959562516804, 0.22557178826840785,
					0.07178052989196337, 0.2306893733652777, 0.21145406072980674, 0.385480059084195, 0.21497116863585,
					0.27357387085383866, 0.1767064169137732, 0.12681780045041896, 0.13592242007403846,
					0.3276447004060276, 0.19003896628776615, 0.2840954431066873, 0.18529129374712008,
					0.17979650579189443, 0.09917170273173849, 0.19620787967742745, 0.22307877231735485,
					0.14717973697028738, 0.20727059323797442, 0.11731679960920674, 0.17440867968553372,
					0.1495128886859581, 0.18039639310004033, 0.14709919880278038 };
			blocklist = dataService.getIndustryList();
			for (int i = 0; i < blocklist.size(); i++) {
				List<IndustryInf> stockId = dataService.getIndustryInf_cname(blocklist.get(i));
				FieldRankInf inf = new FieldRankInf();
				List<BlockHistoryData> relist=dataService.getHistoryData_industry(blocklist.get(i));

				inf.stockranks=relist;
				inf.cname = blocklist.get(i);
				ArrayList<Double> amount = FieldHelper.calIndex(relist);
				inf.basesum = basenum1[i];
				inf.index = (int) (amount.get(0) * 1000 / inf.basesum);
				inf.sumAmount = DataHelper.controldigit(amount.get(0) * 1000);
				// System.out.println(amount.get(1) + ",");
				resultlist.add(inf);
			}
			break;
		case Concept:
			double[] basenum2 = { 0.08030806295611308, 0.10685648686346162, 0.12351421326681158, 0.18431310571682255,
					0.2025811558691032, 0.12650206161922103, 0.19852533268115724, 0.13905327014611885,
					0.11350001187221517, 0.09136271135943262, 0.09513244195456802, 0.08697611678780098,
					0.08419454014683869, 0.2539478113189445, 0.1130889895984171, 0.09964517109188153,
					0.10623931544113682, 0.15673288478574052, 0.10507487398710341, 0.14007568491707312,
					0.12911438976109146, 0.12141015162532981, 0.24490941980685815, 0.14416932616200734,
					0.11597978451361622, 0.331001927001668, 0.0874893560697238, 0.11034497892096358,
					0.20468767297887636, 0.11152849758568968, 0.09813512396566754, 0.13851741886790875,
					0.21027056458718832, 0.1703036759270235, 0.13682715549355556, 0.17038135897257434,
					0.11688421642912881, 0.10823052956208729, 0.19192259785097396, 0.14744441777360767,
					0.12195552943965844, 0.19069705490071387, 0.07549759844701867, 0.1603760229425971,
					0.08552673784425308, 0.1340312566443319, 0.22409413020961597, 0.12674562315656904,
					0.13024533206309058, 0.12112720590380162, 0.15709467504591224, 0.1267251562774281,
					0.17113311006429874, 0.10556991076990865, 0.14483524828780384, 0.09681889374728915,
					0.12189333793579565, 0.14041616531004444, 0.11961956879285536, 0.12098169992489724,
					0.12313539436040617, 0.0857998844261764, 0.10830635031882575, 0.11693160434458047,
					0.07867244922770983, 0.09327592593098187, 0.12075800499141266, 0.07911869634714366,
					0.12356693550009848, 0.20106065942648021, 0.13645058222811485, 0.15207526396223778,
					0.10850451881037468, 0.10445716476124042, 0.20419604282208237, 0.19187741036026812,
					0.14349478094784182, 0.16829143576628763, 0.07425694881529489, 0.21399083486626236,
					0.22903295738450757, 0.14553426338952175, 0.11084965030033117, 0.09021558426626196,
					0.14615544457911997, 0.11674691682367898, 0.1882285572488671, 0.14752840886140564,
					0.0673717936103307, 0.0889442965461888, 0.11423479964785736, 0.2654917287576881,
					0.18864104216786903, 0.10901119411034216, 0.1330685844987716, 0.1348127786079257,
					0.10698222716612898, 0.11547170412001825, 0.1383179095274998, 0.11515337058841564,
					0.29451585255258994, 0.13241645566780028, 0.1642407829270262, 0.09340387278153824,
					0.13274162209396861, 0.10662549188171208, 0.1499715044263354, 0.1415206739002185,
					0.19950274742844687, 0.27450730410233065, 0.11404455456044053, 0.10006081176554392,
					0.0702450061567604, 0.1724901736661039, 0.13113928933049368, 0.20461802515394376,
					0.10780174527959874, 0.10677071321663238, 0.09997716618545595, 0.12727013972012474,
					0.10176901704071363, 0.13744904746670397, 0.07748472043993068, 0.19109896208147878,
					0.12205242264258633, 0.2937591853222343, 0.09937632354509938, 0.27953422530568484,
					0.0999095482942177, 0.12703659563549538, 0.19059978181222215, 0.1261365435809595,
					0.17712794073086513, 0.08840970352238109, 0.19916323453755572, 0.09997020964244853,
					0.24695334681767203, 0.16059676091859423, 0.10435948497656897, 0.20265847753684266,
					0.13122497152887166, 0.13247140481725708, 0.17300795741884958, 0.07263343503397718,
					0.2368407387770496, 0.21183127284530381, 0.11345042824556448, 0.20262468329522818,
					0.1518531439329486, 0.12979835189973535, 0.15052203795221494, 0.10094647921873276,
					0.17965584996200878, 0.13718012211205433, 0.11050576481585743, 0.08935637817044985 };
			blocklist = dataService.getConceptList();
			for (int i = 0; i < blocklist.size(); i++) {
				FieldRankInf inf = new FieldRankInf();
				List<BlockHistoryData> relist=dataService.getHistoryData_concept(blocklist.get(i));

				inf.stockranks=relist;
				inf.cname = blocklist.get(i);
				ArrayList<Double> amount = FieldHelper.calIndex(relist);
				inf.basesum = basenum2[i];
				inf.index = (int) (amount.get(0) * 1000 / inf.basesum);
				inf.sumAmount = DataHelper.controldigit(amount.get(0) * 1000);
				// System.out.println(amount.get(1) + ",");
				resultlist.add(inf);
			}
			break;
		case Area:
			double[] basenum3 = { 0.1330235696983513, 0.12801890486389492, 0.09857306188538785, 0.13219474202332923,
					0.10719742030426774, 0.16068932337488376, 0.11187030816652298, 0.1300990775503408,
					0.10793688546241503, 0.13109600948670572, 0.08208891559819746, 0.15946759196476062,
					0.1203651773780752, 0.1047893584601706, 0.14647348973586646, 0.16808211128790024,
					0.12871400109903616, 0.14674113719400594, 0.16629960976216274, 0.10972160861769795,
					0.1739758619844472, 0.12483326864945303, 0.11881009227449082, 0.11446059438221766,
					0.1497820005316132, 0.2130402554505084, 0.1439469336361459, 0.09848929898850174,
					0.13972508427874952, 0.17192534791142638, 0.12406092817079792, 0.12186813559124018 };
			blocklist = dataService.getAreaList();
			for (int i = 0; i < blocklist.size(); i++) {
				
				FieldRankInf inf = new FieldRankInf();
				List<BlockHistoryData> relist=dataService.getHistoryData_area(blocklist.get(i));

				inf.stockranks=relist;
				inf.cname = blocklist.get(i);
				ArrayList<Double> amount = FieldHelper.calIndex(relist);
				inf.basesum = basenum3[i];
				inf.index = (int) (amount.get(0) * 1000 / inf.basesum);
				inf.sumAmount = DataHelper.controldigit(amount.get(0) * 1000);
				// System.out.println(amount.get(1) + ",");
				resultlist.add(inf);
			}
			break;
		default:
			return null;
		}

//		for (int t = 0; t < resultlist.size(); t++) {
//			System.out.println(// resultlist.get(t).cname+
//					resultlist.get(t).basesum + ",");
//		}
		// 排名前5
		int blocknum = 7;
		int listnum = Math.min(blocknum, resultlist.size());
		for (int k = 0; k < listnum; k++) {
			for (int t = k + 1; t < resultlist.size(); t++) {
				if (resultlist.get(k).index < resultlist.get(t).index) {
					FieldRankInf temp = resultlist.get(t);
					resultlist.set(t, resultlist.get(k));
					resultlist.set(k, temp);
				}
			}
		}
		while (resultlist.size() > blocknum) {
			resultlist.remove(resultlist.size() - 1);
		}
		// 得到股票的顺序 注意停牌的股票
		for (int i = 0; i < listnum; i++) {
			List<BlockHistoryData> stocks = resultlist.get(i).stockranks;
			int stocknum = 5;
			int actualnum = Math.min(stocknum, stocks.size());
			for (int k = 0; k < actualnum; k++) {
				for (int t = k + 1; t < stocks.size(); t++) {
					if (stocks.get(k).getTurnover() < stocks.get(t).getTurnover()) {
						BlockHistoryData temp = stocks.get(k);
						stocks.set(k, stocks.get(t));
						stocks.set(t, temp);
					}
				}
			}
			while (stocks.size() > stocknum) {
				stocks.remove(stocks.size() - 1);
			}
			resultlist.get(i).stockranks = stocks;
		}
		/**
		 * test
		 */
		 for (int i = 0; i < resultlist.size(); i++) {
		 System.out.println(
		 resultlist.get(i).cname + " " + resultlist.get(i).index + " " +
		 resultlist.get(i).sumAmount);
		 for (int k = 0; k < resultlist.get(i).stockranks.size(); k++) {
		 System.out.println(
		 resultlist.get(i).stockranks.get(k).getName() + " " +
		 resultlist.get(i).stockranks.get(k).getTurnover());
		 }
		 }
		return resultlist;
	}

	public static void main(String[] args) throws Exception {
		FieldServiceImpl here = new FieldServiceImpl();
		here.getHotBlock(FieldType.Concept);

		// List<String> blocklist = null;
		// DataService dataService = new DataServiceImpl();
		//
		// blocklist = dataService.getConceptList();
		// System.out.println(blocklist.size());
		// for (int i = 0; i < blocklist.size(); i++) {
		// List<ConceptInf> stockId =
		// dataService.getConceptInf_cname(blocklist.get(i));
		// ArrayList<String> stocklist = new ArrayList<String>();
		// FieldRankInf inf = new FieldRankInf();
		// for (int j = 0; j < stockId.size(); j++) {
		// String sid = stockId.get(j).getSid();
		// stocklist.add(sid);
		// System.out.println(sid);
		// }
		// System.out.println(blocklist.get(i));
		// }
	}
}
