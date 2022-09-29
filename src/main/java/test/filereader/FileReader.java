package test.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author abhishekmukhopadhyay
 */
public class FileReader implements CommandLineRunner
{

    private static Logger LOG = LoggerFactory
            .getLogger(FileReader.class);

    public static void main(String[] args)
    {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(FileReader.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception
    {
        LOG.info("EXECUTING : command line runner");

        for (int i = 0; i < args.length; ++i)
        {
            LOG.info("args[{}]: {}", i, args[i]);
        }
        
        System.out.println("Getting path");
        LOG.info("Getting path");
        Set<String> s = listFilesUsingFilesList(args[0]);
        for(String sx:s)
        {
            LOG.info(sx);
        }
    }
    
    public Set<String> listFilesUsingFilesList(String dir) throws IOException {
    try (Stream<Path> stream = Files.list(Paths.get(dir))) {
        return stream
          .filter(file -> !Files.isDirectory(file))
          .map(Path::getFileName)
          .map(Path::toString)
          .collect(Collectors.toSet());
    }
}
}
