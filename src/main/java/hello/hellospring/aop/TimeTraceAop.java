package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //해당 어노테이션이 있어야 AOP로 사용가능
@Component //이거로 해도되는데 AOP쓴다는 것을 명시적으로 알리기 위해서 config 파일에 넣어둠(쨋든 빈등록을 해야함)
public class TimeTraceAop {

    //공통관심사항을 어디에 적용할 것인가 -> @Around로 타겟팅
    @Around("execution(* hello.hellospring..*(..))") // hello.hellospring 패키지 하위에는 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //다음 메서드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
