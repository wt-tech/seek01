package com.wt.seek.tool;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.wt.seek.entity.Address;
import com.wt.seek.entity.Seek;

/**
 * 寻亲/人 相似度计算
 * @author Daryl
 */
public class SeekSimilarityCalculation {
	
	
	/**
	 * 从list中查找和seek相似的记录并返回.
	 * @param seek
	 * @param list
	 * @return 返回和seek相似的记录
	 */
	public static List<Seek> similarSeekInfoFilter(Seek seek,List<Seek> list){
		List<Seek> similarList = new LinkedList<Seek>();
		
		if(list == null || list.size() == 0)
			return similarList;
		
		for(Seek seek2 : list) {
			if(SeekSimilarityCalculation.isSimilar(seek, seek2, Constants.MATCHS)) {
				similarList.add(seek2);
			}
		}
		return similarList;
	}
	
	
	/**
	 * 
	 * @param seek 当前发布的寻人信息
	 * @param history 该用户之前发布的寻人信息
	 * @return true 当前寻人信息为重复发布,不允许发布;<br/>
	 * false 非重复发布,允许发布
	 */
	public static boolean hadPublishedSameInfoBefore(Seek seek,List<Seek> history) {
		if(history == null || history.size() == 0)
			return false;
		for(Seek seek2 : history) {
			if(SeekSimilarityCalculation.isSimilar(seek, seek2, Constants.REPEATPUBLISHTHRESHOLD))
				return true;
		}
		return false;
	}
	
	
	private static boolean isSimilar(Seek seek1,Seek seek2,double threshhold) {
		double score = 0;
		score += SeekSimilarityCalculation.calName(seek1.getMissName(), seek2.getMissName(), 20);
		score += SeekSimilarityCalculation.calGender(seek1.getGender(), seek2.getGender(), 20);
		score += SeekSimilarityCalculation.calDate(seek1.getBirthdate(), seek2.getBirthdate(), 30);
		score += SeekSimilarityCalculation.calDate(seek1.getMissDate(), seek2.getMissDate(), 30);
		score += SeekSimilarityCalculation.calPlace(seek1.getAddress(), seek2.getAddress(), 40);
		return score >= threshhold;
	}
	
	/**
	 * 名字不计算在总分100分内,
	 * 但是,名字相同或相近会有附加分.
	 */
	private static double calName(String name1,String name2,double score) {
		if(name1 == null || name2 == null || name1.length() == 0 || name2.length() == 0)
			return 0;
		char[] arr1 = name1.toCharArray();
		int sameCharacterCnt = 0;
		for(char ch : arr1) {
			if(name2.contains(ch+"")) 
				sameCharacterCnt++;
		}
		return (score * 1.0 * sameCharacterCnt)/name1.length();
	}
	
	/**
	 * 出生地和失踪地计算
	 * @return
	 */
	private static double calPlace(Address add1,Address add2,double score) {
		if(add1 == null || add2 == null)
			return score/5;
		//出生地:
		score = score/2;
		double finalScore = 0;
		
		if(add1.getBirthProvinceName() == null || add2.getBirthProvinceName() == null)
			finalScore = score/5;
		else {
			if(add1.getBirthProvinceId() == add2.getBirthProvinceId())//省份相同
				finalScore += score/4;
			if(add1.getBirthCityId() == add2.getBirthCityId())//城市相同,到这里已经很不容易了,所以加了一半的分.
				finalScore += score/2;
			if(add1.getBirthCountyId() == add2.getBirthCountyId())//区县相同
				finalScore += score/4;
		}
			
		
		//失踪地:
		if(add1.getMissProvinceName() == null || add2.getMissProvinceName() == null)
			finalScore = score/5;
		else {
			if(add1.getMissProvinceId() == add2.getMissProvinceId())
				finalScore += score/4;
			if(add1.getMissCityId() == add2.getMissCityId()) 
				finalScore += score/2;
			if(add1.getMissCountyId() == add2.getMissCountyId()) 
				finalScore += score/4;
		}
		
		return finalScore;
	}
	
	/**
	 * 性别不同会扣分,相同不加分.
	 */
	private static double calGender(String gender1,String gender2,double score) {
		if(gender1 == null || gender2 == null)
			return 0;
		return gender1.equals(gender2)?0:-1*score;
	}
	
	/**
	 * 出生日期和失踪日期相似度计算.
	 * @return
	 */
	private static double calDate(Date date1,Date date2,double score) {
		if(date1 == null || date2 == null)
			return score/2.0;
		int diffDays1 =(int)((new Date().getTime() - date1.getTime())/(1000*3600*24));
		int diffDays2 =(int)((new Date().getTime() - date2.getTime())/(1000*3600*24));
		int maxDiff = Math.max(diffDays1, diffDays2);
		
		return maxDiff==0?score : score*(Math.abs(diffDays2-diffDays1)*1.0/maxDiff);
	}

	
	
	
}
