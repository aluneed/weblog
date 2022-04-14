package dev.benkyou.weblog.content;

import java.util.List;

public interface ContentService {

    Page<ContentMetadata> getContentPage(ContentMetadata contentMetadata, Integer pageIndex, Integer pageSize);

    ContentMetadata getContentByPathName(String pathName);

    ContentMetadata getContentById(Long id);

    Long save(ContentMetadata contentMetadata);

    Long update(ContentMetadata contentMetadata);

    Long delete(Long id);

}
