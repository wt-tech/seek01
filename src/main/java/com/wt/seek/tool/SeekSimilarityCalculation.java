package com.wt.seek.tool;

import java.text.SimpleDateFormat;
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
		
		if(add1.getBirthProvinceId() == null || add2.getBirthProvinceId() == null)
			finalScore = score/5;
		else {
			if(add1.getBirthProvinceId().equals(add2.getBirthProvinceId()))//省份相同
				finalScore += score/4;
			if(add1.getBirthCityId() != null && add1.getBirthCityId().equals(add2.getBirthCityId()))//城市相同,到这里已经很不容易了,所以加了一半的分.
				finalScore += score/2;
			if(add1.getBirthCountyId() != null && add1.getBirthCountyId().equals(add2.getBirthCountyId()))//区县相同
				finalScore += score/4;
		}
		//失踪地:
		if(add1.getMissProvinceId() == null || add2.getMissProvinceId() == null)
			finalScore += score/5;
		else {
			if(add1.getMissProvinceId().equals(add2.getMissProvinceId()))
				finalScore += score/4;
			if(add1.getMissCityId() != null && add1.getMissCityId().equals(add2.getMissCityId())) 
				finalScore += score/2;
			if(add1.getMissCountyId() != null && add1.getMissCountyId().equals(add2.getMissCountyId())) 
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
		System.err.println(new SimpleDateFormat("yyyy-MM-dd").format(date1));
		System.err.println(new SimpleDateFormat("yyyy-MM-dd").format(date2));
		int diffDays1 =(int)((new Date().getTime() - date1.getTime())/(1000*3600*24));
		int diffDays2 =(int)((new Date().getTime() - date2.getTime())/(1000*3600*24));
		int maxDiff = Math.max(diffDays1, diffDays2);
		int minDiff = Math.min(diffDays1, diffDays2);
		
		double finalScore = 0;
		if(maxDiff == 0) {
			finalScore = score;
		}else if(maxDiff < Constants.DAYS_30)//当距离今日天数小于30天时,返回的分数最小为0,不会出现负值
			finalScore = score*((maxDiff-Math.abs(diffDays2-diffDays1))*1.0/maxDiff);
		else {//返回的分数会出现负值,最小为 -score
			double times = 1.0*(maxDiff-minDiff)/maxDiff;
			if(times <= Constants.ONE_THIRD)//线性函数   当 0<times<1/3,score是正数
				finalScore = (Constants.BELOW_ZERO_3*score)*times + score;
			else // 1/3<score<1   score是负数
				finalScore = (-1.5*score)*times + 0.5*score;
		}
		return finalScore;
	}

}
