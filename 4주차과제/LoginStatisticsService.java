import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 로그인 통계를 제공하는 서비스 클래스
 * - 월별 접속자 수 조회
 * - 일자별 접속자 수 조회
 * - 평균 하루 로그인 수 조회
 * - 휴일 제외 로그인 수 조회
 * - 부서별 월별 로그인 수 조회
 */
public class LoginStatisticsService {

    private final LoginMapper loginMapper;
    private final HolidayService holidayService;

    /**
     * 생성자: 로그인 및 휴일 정보를 관리하는 Mapper와 Service를 주입받음
     */
    public LoginStatisticsService(LoginMapper loginMapper, HolidayService holidayService) {
        this.loginMapper = loginMapper;
        this.holidayService = holidayService;
    }

    /**
     * 1. 월별 접속자 수 조회
     * @param year 조회할 연도
     * @return 월별 로그인 사용자 수
     */
    public Map<String, Long> getMonthlyLoginCount(String year) {
        return loginMapper.getMonthlyLoginCount(year);
    }

    /**
     * 2. 일자별 접속자 수 조회
     * @param year 조회할 연도
     * @param month 조회할 월
     * @return 일자별 로그인 사용자 수
     */
    public Map<LocalDate, Long> getDailyLoginCount(String year, String month) {
        return loginMapper.getDailyLoginCount(year, month);
    }

    /**
     * 3. 평균 하루 로그인 수 조회
     * @param year 조회할 연도
     * @return 평균 하루 로그인 수
     */
    public double getAverageDailyLoginCount(String year) {
        return loginMapper.getAverageDailyLoginCount(year);
    }

    /**
     * 4. 휴일 제외 로그인 수 조회
     * - DB에서 모든 로그인 날짜를 가져와 휴일과 비교하여 필터링
     * @param year 조회할 연도
     * @return 휴일을 제외한 로그인 수
     */
    public long getLoginCountExcludingHolidays(String year) {
        List<LocalDate> allLoginDates = loginMapper.findAllLoginDates(year);
        Set<LocalDate> holidays = holidayService.getHolidaysFromDB(year);
        return allLoginDates.stream().filter(date -> !holidays.contains(date)).count();
    }

    /**
     * 5. 부서별 월별 로그인 수 조회
     * @param year 조회할 연도
     * @param month 조회할 월
     * @return 부서별 월별 로그인 사용자 수
     */
    public Map<String, Map<String, Long>> getDepartmentMonthlyLoginCount(String year, String month) {
        return loginMapper.getDepartmentMonthlyLoginCount(year, month);
    }
}
