package acc.microservices.limitsservice.components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Limits {
    private Integer minimun;
    private Integer maximun;


    public Limits(Integer minimun, Integer maximun) {
        this.minimun = minimun;
        this.maximun = maximun;
    }

    
}
