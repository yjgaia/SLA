package sla.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class DateUtil {
	public static String[] dayNames={"일","월","화","수","목","금","토"}; 
	static String zone="Asia/Seoul";
	
	/**
	 * 오늘의 Calendar를 가져옴 // 다른 함수에서 쓰기 용도
	 * @return
	 */
	public static Calendar getCalendar()
    {
        TimeZone tz = TimeZone.getTimeZone(zone);
        Calendar cal = new GregorianCalendar(tz);
        return cal;
    }
	
	/**
	 * 특정 년, 월의 calendar를 가져옴 (주의 : month 는 0부터 시작 ex) 1월 - 0 ,2월 - 1 ....)
	 * @param year
	 * @param month
	 * @return
	 */
	public static Calendar getCalendar(int year,int month)
    {
        Calendar cal = new GregorianCalendar(year,month,1);
        return cal;
    }
	
	/**
	 * 특정 년, 월,일의 calendar를 가져옴 (주의 : month 는 0부터 시작 ex) 1월 - 0 ,2월 - 1 ....)
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Calendar getCalendar(int year,int month,int day)
    {
        Calendar cal = new GregorianCalendar(year,month,day);
        return cal;
    }
	
	public static Calendar getCalendar(int year,int month,int day,int hour)
    {
        Calendar cal = new GregorianCalendar(year,month,day,hour,0);
        return cal;
    }
	
	/**
	 * 오늘 날짜를 넘어온 dateForm에 따라 리턴 시켜줌 ex) getToday("YYYY년 MM월 DD일") -> "2011년 11월 28일" 리턴
	 * @param dateForm
	 * @return
	 */
	public static String getToday(String dateForm)
    {
		String date=dateForm;
        Calendar cal = getCalendar();
        //cal.add(2, 1);
        String year=dayIntToString(cal.get(1));
        String month=dayIntToString(cal.get(2)+1);
        String day=dayIntToString(cal.get(5));
        String hour=dayIntToString(cal.get(11));
        date=date.replaceAll("YYYY", year);
        date=date.replaceAll("MM", month);
        date=date.replaceAll("DD", day);
        date=date.replaceAll("HH", hour);
        return date;
    }
	
	/**
	 * 넘어온 date를 dateForm에 맞게 변형 시켜줌( 주의: date는 YYYYMMDD 형식이여야함)
	 * ex) getDayString("20111128","YYYY-MM-DD") -> 2011-11-28 리턴
	 * @param date
	 * @param dateForm
	 * @return
	 */
	public static String getDayString(String date,String dateForm)
    {
		String result=dateForm;
		if(date!=null){
			if(date.length()>7){
        		String year=date.substring(0, 4);
        		String month=date.substring(4, 6);
        		String day=date.substring(6, 8);
        	 	result=result.replaceAll("YYYY", year);
             	result=result.replaceAll("MM", month);
             	result=result.replaceAll("DD", day);
             	return result;
        	}else{
        		return date;
        	}
		}else{
			return date;
		}
        
    }
	
	/**
	 * 주어진 Calendar의 날짜를 넘어온 dateForm에 따라 리턴해줌
	 * @param calendar
	 * @param dateForm
	 * @return
	 */
	public static String getDateString(Calendar calendar,String dateForm){
		String date=dateForm;
        Calendar cal = calendar;
        String year=dayIntToString(cal.get(1));
        String month=dayIntToString(cal.get(2)+1);
        String day=dayIntToString(cal.get(5));
        String hour=dayIntToString(cal.get(11));
        date=date.replaceAll("YYYY", year);
        date=date.replaceAll("MM", month);
        date=date.replaceAll("DD", day);
        date=date.replaceAll("HH", hour);
        return date;
	}
	
	/**
	 * 오늘의 요일을 구하는 함수
	 * @return
	 */
	public static String getTodayDay(){
        Calendar cal = getCalendar();
        return weekDay(cal.get(Calendar.DAY_OF_WEEK));
	}
	
	/**
	 * 넘어온 date의 요일을 구하는 함수 (주의 : date는 YYYYMMDD 형식이여야함)
	 * @param date
	 * @return
	 */
	public static String getDayName(String date){
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(4,6))-1;
		int day=Integer.parseInt(date.substring(6,8));
		Calendar cal = getCalendar();
		cal.set(year, month, day);
		 return weekDay(cal.get(Calendar.DAY_OF_WEEK));
	}
	
	/**
	 * 요일을 int값을 한글로 변환
	 * @param dayOfWeek
	 * @return
	 */
	public static String weekDay(int dayOfWeek) {
		return dayNames[dayOfWeek-1];
	}

	/**
	 * 10일 미만의 날은 앞에 0을 붙여주는 함수(2자리수 유지를 위함)
	 * @param day
	 * @return
	 */
	public static String dayIntToString(int day){
		if(day<10){
			return "0"+String.valueOf(day);
		}else{
			return String.valueOf(day);
		}
	}
	
	/**
	 * 주어진 년,월의 달력을 List<List<String>>형식으로 리턴해줌.
	 * 최상위 List는 한 주, 속의 List<String>은 한 주의 날짜들(ex:20111128) 들이 들어있음
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<List<String>> getCalendarWithValue(int year,int month){
		List<List<String>> resultList=new ArrayList<List<String>>();
		Calendar cal=getCalendar(year,month-1);
		Calendar prevMonthCal=getCalendar(year,month-2);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int prevMonthMaxDay=prevMonthCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int week=cal.get(Calendar.DAY_OF_WEEK);
		int startDay=1;
		int nextMonthStartDay=1;
		
		while(startDay<maxDay){
			if(resultList.size()==0){
				List<String> firstWeekList=new ArrayList<String>();
				int firstDayOfPrevMonthInWeek=prevMonthMaxDay-(week-2);
				for(int j=0;j<week-1;j++){
					firstWeekList.add(year+dayIntToString(month-1)+dayIntToString(firstDayOfPrevMonthInWeek));
					firstDayOfPrevMonthInWeek++;
				}
				for(int k=0;k<8-week;k++){
					firstWeekList.add(year+dayIntToString(month)+dayIntToString(startDay));
					startDay++;
				}
				resultList.add(firstWeekList);
			}else{
				List<String> otherWeekList=new ArrayList<String>();
				for(int j=0;j<7;j++){
					if(startDay<=maxDay){
						otherWeekList.add(year+dayIntToString(month)+dayIntToString(startDay));
						startDay++;
					}else{
						otherWeekList.add(year+dayIntToString(month+1)+dayIntToString(nextMonthStartDay));
						nextMonthStartDay++;
					}
				}
				resultList.add(otherWeekList);
			}
		}
		return resultList;
	}
	
	/**
	 * 두 날짜 사이의 날짜들을 List<String> 으로 리턴해줌 (주의 : YYYY-MM-DD 형식으로 넘겨야함)
	 * ex) getBetweenDays("2011-11-28","2011-12-01") -> 2011-11-28,2011-11-29,2011-11-30,2011-12-01 을 List로 넘겨줌
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getBetweenDays(String startDate,String endDate){
		List<String> resultList=new ArrayList<String>();
		if(startDate!=null&&endDate!=null){
			String[] startDateArray=startDate.split("-");
			String[] endDateArray=endDate.split("-");
			Calendar startCalendar=getCalendar(Integer.parseInt(startDateArray[0]),Integer.parseInt(startDateArray[1])-1);
			int startCalendarMaxday=startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int startMonth=Integer.parseInt(startDateArray[1]);
			int endMonth=Integer.parseInt(endDateArray[1]);
			int startDay=Integer.parseInt(startDateArray[2]);
			int endDay=Integer.parseInt(endDateArray[2]);
			if(startDateArray[1].equals(endDateArray[1])){
				if(startDay<=endDay){
					while(startDay<=endDay){
						resultList.add(startDateArray[0]+"-"+startDateArray[1]+"-"+dayIntToString(startDay));
						startDay++;
					}
				}
			}else{
				if(startDay<=startCalendarMaxday){
					
					if(endMonth<startMonth){
						List<String> thisYear=getBetweenDays(startDate, startDateArray[0]+"-12-31");
						List<String> nextYear=getBetweenDays(endDateArray[0]+"-01-01", endDate);
						resultList.addAll(thisYear);
						resultList.addAll(nextYear);
					}else{
						while(startDay<=startCalendarMaxday){
							resultList.add(startDateArray[0]+"-"+startDateArray[1]+"-"+dayIntToString(startDay));
							startDay++;
						}
						int monthDifference=endMonth-startMonth;
						
						for(int i=0;i<monthDifference;i++){
							if(i==monthDifference-1){//마지막 달
								for(int j=1;j<=endDay;j++){
									resultList.add(endDateArray[0]+"-"+endDateArray[1]+"-"+dayIntToString(j));
								}
							}else{
								Calendar calendar=getCalendar(Integer.parseInt(endDateArray[0]),Integer.parseInt(startDateArray[1])+i+1);
								int maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
								for(int j=1;j<=maxDay;j++){
									resultList.add(endDateArray[0]+"-"+(Integer.parseInt(startDateArray[1])+i+1)+"-"+dayIntToString(j));
								}
							}
						}
					}
				}
			}
		}
		return resultList;
		
	}
	
	/**
	 * 이전달 or 다음달을 구해줌, plusMinus : 0 -> 이번달을 리턴 , plusMinus : 1 -> 한달뒤
	 * plusMinus:2 -> 두달뒤, plusMinus: -1 -> 한달 전 ....
	 * @param year
	 * @param month
	 * @param plusMinus
	 * @param dateForm
	 * @return
	 */
	public static String getBeforeOrNextMonth(int year,int month,int plusMinus){
		return getDateString(getCalendar(year,month-1+plusMinus),"YYYYMM");
	}
	
	public static String getBeforeOrNextDay(int year,int month,int day,int plusMinus){
		return getDateString(getCalendar(year,month-1,day+plusMinus),"YYYYMMDD");
	}
	
	public static String getBeforeOrNextHour(int year,int month,int day,int hour,int plusMinus){
		int sum=hour+plusMinus;
		int tempDay=day;
		int tempHour=0;
		if(sum>=0){
			tempDay+=(sum/24);
			tempHour=sum%24;
		}else{
			tempDay--;
			tempDay+=(sum/24);
			tempHour=24+sum%24;
		}
		return getDateString(getCalendar(year,month-1,tempDay,tempHour),"YYYYMMDDHH");
	}
	public static void main(String[] args){
		System.out.println(getBeforeOrNextHour(2012, 11, 4,6,-10));
	}
	
	/**
	 * 주어진 날짜의 주를 옵션에 따라 String[]로 리턴해줌
	 * @param year
	 * @param month
	 * @param day
	 * @param resultForm
	 * @param dayGubun
	 * @param startDay
	 * @return
	 */
	public static String[] getThisWeek(int year,int month,int day,String resultForm,int dayGubun,int startDay){
		//dayGubun- 0: 한 주의 모든 일, 1: 한 주의 평일, 2: 한 주의 주말
		//startDay- 0: 일,1: 월 , 2: 화, 3: 수, 4: 목, 5: 금, 6:토 부터 한주 시작
		//result[0] : 날짜 시작일 ,result[1] 날짜 종료일 , result[2] : x번째 주 
		Calendar calendar=getCalendar(year,month-1,day);

		int dayOfWeek;
		
		Calendar startCalendar=(Calendar)calendar.clone();
		Calendar endCalendar=(Calendar)calendar.clone();
		dayOfWeek=startCalendar.get(Calendar.DAY_OF_WEEK);
		dayOfWeek=dayOfWeek-startDay;
		if(dayOfWeek<1){
			dayOfWeek=dayOfWeek+7;
		}
		
		switch(dayGubun){
		case 0: // 한 주의 모든 날짜
			startCalendar.add(Calendar.DATE, -(dayOfWeek-1));
			endCalendar.add(Calendar.DATE,7-dayOfWeek);
			break;
		case 1: // 한 주의 평일
			startCalendar.add(Calendar.DATE, -(dayOfWeek-1));
			endCalendar.add(Calendar.DATE,5-dayOfWeek);
			break;
		case 2:// 한 주의 주말
			startCalendar.add(Calendar.DATE, -(dayOfWeek-6));
			endCalendar.add(Calendar.DATE,7-dayOfWeek);
			break;
		}
		
		
		List<String> weekList=getBetweenDays(getDateString(startCalendar,"YYYY-MM-DD"),getDateString(endCalendar,"YYYY-MM-DD"));
		List<String> resultList=new ArrayList<String>();
		int size=weekList.size();
		for(int i=0;i<size;i++){
			String[] date=weekList.get(i).split("-");
			String resultDate=resultForm;
			resultDate=resultDate.replace("YYYY", date[0]);
			resultDate=resultDate.replace("MM", date[1]);
			resultDate=resultDate.replace("DD", date[2]);
			resultList.add(resultDate);
		}
		
		String[] result={getDateString(startCalendar,resultForm),getDateString(endCalendar,resultForm),String.valueOf(startCalendar.get(Calendar.WEEK_OF_MONTH))};
		return result;
	}
	
	
	/**
	 * 이번주를 주어진 폼에 따라 시작일 끝일을 리턴
	 * @param resultForm
	 * @param dayGubun
	 * @param startDay
	 * @return
	 */
	public static String[] getThisWeek(String resultForm,int dayGubun,int startDay){
		String today=DateUtil.getToday("YYYY-MM-DD");
		String[] todayArray=today.split("-");
		return getThisWeek(Integer.parseInt(todayArray[0]),Integer.parseInt(todayArray[1]),Integer.parseInt(todayArray[2]),resultForm,dayGubun,startDay);
	}
	
	public static String getFormedDate(String day){
		if(day!=null){
			String month=day.substring(4,6);
			String date=day.substring(6,8);
			Integer monthInt=Integer.parseInt(month);
			Integer dateInt=Integer.parseInt(date);
			month=monthInt<10?String.valueOf(monthInt):month;
			date=dateInt<10?String.valueOf(dateInt):date;
			return month+"월 "+date+"일("+getDayName(day)+")";
		}else{
			return "";
		}
	}

}
