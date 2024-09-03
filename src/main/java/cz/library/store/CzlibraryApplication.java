package cz.library.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

@SpringBootApplication
@ComponentScan(
    basePackages = "cz.library.store",
    includeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = RemoveModelAndEntitiesFilter.class)
)
public class CzlibraryApplication {

  public static void main(String[] args) {
    SpringApplication.run(CzlibraryApplication.class, args);
  }

}

class RemoveModelAndEntitiesFilter implements TypeFilter {

  @Override
  public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
    String className = metadataReader.getClassMetadata().getClassName();
    return !className.contains("dto")
        && !className.contains("validation")
        && !className.contains("infrastructure")
        && !className.contains("domain");
  }
}
