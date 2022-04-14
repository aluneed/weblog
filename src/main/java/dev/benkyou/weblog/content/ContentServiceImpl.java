package dev.benkyou.weblog.content;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ContentServiceImpl implements ContentService {

    @Inject
    ContentMetadataRepository contentMetadataRepository;

    @Override
    public List<ContentMetadata> getContentList(ContentMetadata contentMetadata, Integer pageIndex, Integer pageSize) {
        //mybatis will be much better here
        String title = contentMetadata.getTitle() == null ? "%" : "%" + contentMetadata.getTitle() + "%";
        String tags = contentMetadata.getTags() == null ? "%" : "%" + contentMetadata.getTags() + "%";
        String category = contentMetadata.getCategory() == null ? "%" : contentMetadata.getCategory();
        String keywords = contentMetadata.getKeywords() == null ? "%" : "%" + contentMetadata.getKeywords() + "%";
        PanacheQuery<ContentMetadata> panacheQuery = ContentMetadata
//                .find("select contentMetadata.title, contentMetadata.tags from ContentMetadata where title like ?1 and tags = ?2",
                .find("(title like ?1 or keywords like ?4) and tags like ?2 and category like ?3",
                        Sort.by("create_time", Sort.Direction.Descending),
                        title,
                        tags,  //todo: multiple tags query
                        category,
                        keywords)
                .page(pageIndex, pageSize);
        return panacheQuery.list();
    }

    @Override
    public ContentMetadata getContentByPathName(String pathName) {
        ContentMetadata contentMetadata = contentMetadataRepository.find("path_name", pathName).firstResult();
        if (contentMetadata == null) {
            return new ContentMetadata();
        }
        Content content = Content.findById(contentMetadata.getId());
        contentMetadata.setContent(content.getContent());
        return contentMetadata;
    }

    @Override
    public ContentMetadata getContentById(Long id) {
        ContentMetadata contentMetadata = ContentMetadata.findById(id);
        if (contentMetadata == null) {
            return new ContentMetadata();
        }
        Content content = Content.findById(id);
        contentMetadata.setContent(content.getContent());
        return contentMetadata;
    }

    @Override
    @Transactional
    public Long save(ContentMetadata contentMetadata) {
        Date now = new Date();
        contentMetadata.setCreateTime(now).setUpdateTime(now).persist();
        new Content().setId(contentMetadata.getId())
                .setPathName(contentMetadata.getPathName())
                .setContent(contentMetadata.getContent())
                .persist();
        return contentMetadata.getId();
    }

    @Override
    @Transactional
    public Long update(ContentMetadata contentMetadata) {
        Long id = contentMetadata.getId();
        ContentMetadata current = ContentMetadata.findById(id);
        current.setPathName(contentMetadata.getPathName())
                .setTags(contentMetadata.getTags())
                .setCategory(contentMetadata.getCategory())
                .setTitle(contentMetadata.getTitle())
                .setKeywords(contentMetadata.getKeywords())
                .setIntroduction(contentMetadata.getIntroduction())
                .setUpdateTime(new Date());
        Content content = Content.findById(id);
        content.setPathName(contentMetadata.getPathName()).setContent(contentMetadata.getContent());
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        Content.deleteById(id);
        ContentMetadata.deleteById(id);
        return null;
    }

}
