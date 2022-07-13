package Hi.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // lombok의 어노테이션을 넣어서 코드를 줄였다
public class HelloData {

    private String username;
    private  int age;
}
