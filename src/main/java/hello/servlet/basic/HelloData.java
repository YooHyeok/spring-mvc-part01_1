package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * JSON 파싱용 VO 객체
 */
@Getter @Setter
public class HelloData {
    private String username;
    private int age;

}
