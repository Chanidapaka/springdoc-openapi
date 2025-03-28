package sit.int204.sampleexception;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sit.int204.sampleexception.utils.ListMapper;

@SpringBootApplication
public class SampleExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleExceptionApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ListMapper listMapper() {
        return ListMapper.getInstance();
    }
}
