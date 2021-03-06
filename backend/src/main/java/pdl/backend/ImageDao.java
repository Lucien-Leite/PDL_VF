package pdl.backend;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDao implements Dao<Image> {

  private final Map<Long, Image> images = new HashMap<>();

  public ImageDao() {

    List<String> filenames = new ArrayList<>();

    try (
            InputStream in = getResourceAsStream("./images");
            BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
        String resource;

        while ((resource = br.readLine()) != null) {
            filenames.add(resource);
        }
    }catch (final IOException e) {
      e.printStackTrace();
    }
  

    for (String string : filenames) {
      final ClassPathResource imgFile = new ClassPathResource("images/"+string);
    byte[] fileContent;
    try {
      fileContent = Files.readAllBytes(imgFile.getFile().toPath());
      Image img = new Image(string, fileContent);
      images.put(img.getId(), img);
    } catch (final IOException e) {
      e.printStackTrace();
    }
    }

  }

  @Override
  public Optional<Image> retrieve(final long id) {

    return Optional.ofNullable(images.get(id));
  }

  @Override
  public ArrayList<Image> retrieveAll() {

    return new ArrayList<Image>(images.values());
  }

  @Override
  public void create(final Image img) {
    images.put(img.getId(), img);
  }

  @Override
  public void update(final Image img, final String[] params) {
    img.setName(Objects.requireNonNull(params[0], "Name cannot be null"));

    images.put(img.getId(), img);
  }

  @Override
  public void delete(final Image img) {
    images.remove(img.getId());
  }


private InputStream getResourceAsStream(String resource) {
    final InputStream in
            = getContextClassLoader().getResourceAsStream(resource);

    return in == null ? getClass().getResourceAsStream(resource) : in;
}

private ClassLoader getContextClassLoader() {
    return Thread.currentThread().getContextClassLoader();
}
}
