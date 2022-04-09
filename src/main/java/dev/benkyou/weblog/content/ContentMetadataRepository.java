package dev.benkyou.weblog.content;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContentMetadataRepository implements PanacheRepository<ContentMetadata> {

}
