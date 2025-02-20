import java.time.LocalDate;
import java.util.*;


/**
 * 로그인 통계를 제공하는 서비스 클래스
 * - 월별 접속자 수 조회
 * - 일자별 접속자 수 조회
 * - 평균 하루 로그인 수 조회
 * - 휴일 제외 로그인 수 조회
 * - 부서별 월별 로그인 수 조회
 */
public class LoginStatisticsService {
    private final LoginRespository loginRepository;
    private final HolidayService holidayService;

    /**
     * 생성자: 로그인 및 휴일 정보를 관리하는 Repository를 주입받음
     */
    public LoginStatisticsService(LoginRepository loginRepository, HolidayService holidayService) {
        this.loginRepository = loginRepository;
        this.holidayService = holidayService;
    }

    public Map<String, Long> getMonthlyLoginCount(String year) {
        return loginRepository.excuteSQL()
    }
}