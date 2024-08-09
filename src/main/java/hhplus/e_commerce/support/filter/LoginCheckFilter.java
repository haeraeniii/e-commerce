package hhplus.e_commerce.support.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/api/customers/register", "/api/products"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // [ Servlet -> HttpServlet 변환 ]
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        try {
            if(isLoginCheckPath(requestURI)) { // 화이트리스트 여부 체크
                // 세션 가져오기
                HttpSession session = httpRequest.getSession(false);
                System.out.println(session.getAttribute("userId"));
                // 세션이 없거나 회원정보가 없는 경우
                if(session == null || session.getAttribute("userId") == null){
                    log.info("로그인이 필요합니다.");
                    return; // 프로세스 종료
                }
            }
        } catch (Exception e){
            throw e;
        }finally {
            log.info("인증 체크 필터 종료 = {}", requestURI);
        }
    }

    /*
     * 화이트 리스트인 경우 인증 체크X
     * */
    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist,requestURI);
    }
}
