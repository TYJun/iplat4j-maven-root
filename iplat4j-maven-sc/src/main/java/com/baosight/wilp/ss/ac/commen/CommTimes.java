package com.baosight.wilp.ss.ac.commen;

import com.bonawise.smp.ResponseResult;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName CommTimes
 * @author kangroo
 * @date 2018年3月24日 下午1:10:26
 */
public class CommTimes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8137838733387202360L;

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 食堂时间集合
	 */
	private static Map<String, CanteenTimesEntity> canteenTimes = new HashMap<String, CanteenTimesEntity>();

	/**
	 * 获取食堂时间
	 * @return
	 */
	public static Map<String, CanteenTimesEntity> getCanteenTimes() {
		return CommTimes.canteenTimes;
	}
	
	/**
	 * 设置食堂缓存时间
	 * @param times
	 */
	public static void setCanteenTimes(List<CanteenTimesEntity> times){
		Map<String, CanteenTimesEntity> canteenTimes = new HashMap<String, CanteenTimesEntity>();
		if(times==null || times.size()==0){
			CommTimes.canteenTimes = canteenTimes;
		}else{
			for(CanteenTimesEntity time:times){
				canteenTimes.put(time.getCanteenNum()+"#"+time.getMealNum(), time);
			}
			CommTimes.canteenTimes = canteenTimes;
		}
	}
	
	/**
	 * 根据订餐配置规则校验当前时间和下单时间和送餐时间
	 * @param canteenNum  食堂编码
	 * @param mealNum     餐次
	 * @param needDate    用餐时间
	 * @param sendTime    送达时间
	 * @return
	 * @throws ParseException 
	 */
	public static ResponseResult checkCanteenTimes(String canteenNum,String mealNum,String needDate,String sendTime) throws ParseException{
		ResponseResult result = new ResponseResult("200","");
		if(CommTimes.canteenTimes==null || CommTimes.canteenTimes.size()==0){
//			result.setRespMsg("食堂时间配置异常");
		}else{
			CanteenTimesEntity canteenTime = CommTimes.canteenTimes.get(canteenNum+"#"+mealNum);
			if(canteenTime==null){
//				result.setRespMsg("食堂时间配置异常");
			}else{
				// 当天允许订餐时间
				String todayOrderTime = canteenTime.getTodayOrderTime();
				String today = getDay(false);
				//今天
				if(today.equals(needDate) || "D".equals(needDate)){
					// 限制下单时间
					String noOrderTime = today+" "+todayOrderTime +":00";
					long noOrderTimeLong = format.parse(noOrderTime).getTime();
					// 送达时间
					String noSendTime = today+" "+sendTime +":00";
					long noSendTimeLong = format.parse(noSendTime).getTime();
					int advanceTime = canteenTime.getAdvanceTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if(new Date().getTime()>noOrderTimeLong){
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+noOrderTime);
					}else if(new Date().getTime()>(noSendTimeLong-(advanceTime*60*1000))){
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出送餐提前时间："+format.format(new Date(noSendTimeLong-(advanceTime*60*1000))));
					}
				}
				// 明天允许订餐时间
				String tomorrowOrderTime = canteenTime.getTomorrowOrderTime();
				String tomorrow = getDay(true);
				if(tomorrow.equals(needDate) || "Y".equals(needDate)){
					// 限制下单时间
					String notomorrowTime = today+" "+tomorrowOrderTime +":00";
					long noOrderTimeLong = format.parse(notomorrowTime).getTime();
					// 送达时间
					String noSendTime = tomorrow+" "+sendTime +":00";
					long noSendTimeLong = format.parse(noSendTime).getTime();
					int advanceTime = canteenTime.getAdvanceTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if(new Date().getTime()>noOrderTimeLong){
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+notomorrowTime);
					}else if(new Date().getTime()>(noSendTimeLong-(advanceTime*60*1000))){
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出送餐提前时间："+format.format(new Date(noSendTimeLong-(advanceTime*60*1000))));
					}
				}
			}
		}
		if(!"".equals(result.getRespMsg())){
			result.setRespCode("201");
		}
		return result;
	}
	
	/**
	 * 根据订餐配置规则校验当前时间和需求时间checkPlaceOrderTimes
	 * @param canteenNum  食堂编码
	 * @param mealNum     餐次
	 * @param needDate    用餐时间
	 * @return
	 * @throws ParseException 
	 */
	public static ResponseResult checkPlaceOrderTimes(String canteenNum,String mealNum,String needDate) throws ParseException{
		ResponseResult result = new ResponseResult("200","");
		if(CommTimes.canteenTimes==null || CommTimes.canteenTimes.size()==0){
			
		}else{
			CanteenTimesEntity canteenTime = CommTimes.canteenTimes.get(canteenNum+"#"+mealNum);
			if(canteenTime==null){
			}else{
				// 当天允许订餐时间
				String todayOrderTime = canteenTime.getTodayOrderTime();
				String today = getDay(false);
				//今天
				if(today.equals(needDate) || "D".equals(needDate)){
					// 限制下单时间
					String noOrderTime = today+" "+todayOrderTime +":00";
					long noOrderTimeLong = format.parse(noOrderTime).getTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if(new Date().getTime()>noOrderTimeLong){
						System.out.println("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+noOrderTime);
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+noOrderTime);
					}
				}
				// 明天允许订餐时间
				String tomorrowOrderTime = canteenTime.getTomorrowOrderTime();
				String tomorrow = getDay(true);
				if(tomorrow.equals(needDate) || "Y".equals(needDate)){
					// 限制下单时间
					String notomorrowTime = today+" "+tomorrowOrderTime +":00";
					long noOrderTimeLong = format.parse(notomorrowTime).getTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if(new Date().getTime()>noOrderTimeLong){
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+notomorrowTime);
					}
				}
			}
		}
		if(!"".equals(result.getRespMsg())){
			result.setRespCode("201");
		}
		return result;
	}
	
	/**
	 * 根据订餐配置规则校验当前时间和需求时间checkOrderTimes
	 * @param canteenNum  食堂编码
	 * @param mealNum     餐次
	 * @param needDate    用餐时间
	 * @return
	 * @throws ParseException 
	 */
	public static ResponseResult checkOrderTimes(String canteenNum,String mealNum,String needDate) throws ParseException{
		ResponseResult result = new ResponseResult("200","");
		if(CommTimes.canteenTimes==null || CommTimes.canteenTimes.size()==0){
			result.setRespCode("202");
		}else{
			CanteenTimesEntity canteenTime = CommTimes.canteenTimes.get(canteenNum+"#"+mealNum);
			if(canteenTime==null){
			}else{
				// 当天允许订餐时间
				String todayOrderTime = canteenTime.getTodayOrderTime();
				String today = getDay(false);
				//今天
				if(today.equals(needDate) || "D".equals(needDate)){
					// 限制下单时间
					String noOrderTime = today+" "+todayOrderTime +":00";
					long noOrderTimeLong = format.parse(noOrderTime).getTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if(new Date().getTime()>noOrderTimeLong){
						System.out.println("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+noOrderTime);
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+noOrderTime);
					}
				}
				// 明天允许订餐时间
				String tomorrowOrderTime = canteenTime.getTomorrowOrderTime();
				String tomorrow = getDay(true);
				if(tomorrow.equals(needDate) || "Y".equals(needDate)){
					// 限制下单时间
					String notomorrowTime = today+" "+tomorrowOrderTime +":00";
					long noOrderTimeLong = format.parse(notomorrowTime).getTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if(new Date().getTime()>noOrderTimeLong){
						result.setRespMsg("当前时间为："+format.format(new Date())+"，已超出允许订餐时间："+notomorrowTime);
					}
				}
			}
		}
		if(!"".equals(result.getRespMsg())){
			result.setRespCode("201");
		}
		return result;
	}
	
	/**
	 * 获取当前时间 年-月-日
	 * @param tomorrow  false-获取当天年-月-日、true-获取明天年-月-日
	 * @return
	 */
	private static String getDay(boolean tomorrow){
		Calendar now = Calendar.getInstance();  
		if(tomorrow){
			now.add(Calendar.DAY_OF_MONTH, 1);
		}
        int yesr = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        String value = "";
        value += yesr;
        if(month<10){
        	value += "-0"+month;
        }else{
        	value += "-"+month;
        }
        if(day<10){
        	value += "-0"+day;
        }else{
        	value += "-"+day;
        }
        return value;  
	}
	public static void main(String[] args) {
		System.out.println(getDay(false));
	}
}
