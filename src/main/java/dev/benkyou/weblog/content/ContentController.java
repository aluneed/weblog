package dev.benkyou.weblog.content;


import dev.benkyou.weblog.Rsp;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/content")
@Produces(MediaType.APPLICATION_JSON)
public class ContentController {

    @Inject
    ContentService contentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Rsp<List<ContentMetadata>> contentList(@BeanParam ContentMetadata contentMetadata,
                                                  @DefaultValue("0") @QueryParam("pageIndex") Integer pageIndex,  //页索引从0开始
                                                  @DefaultValue("10") @QueryParam("pageSize") Integer pageSize) {
        List<ContentMetadata> contentMetadataList = contentService.getContentList(contentMetadata, pageIndex, pageSize);
        return Rsp.ok(contentMetadataList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pathName}")
    public Rsp<ContentMetadata> content(@PathParam("pathName") String pathName) {
        ContentMetadata contentMetadata = contentService.getContentByPathName(pathName);
        return Rsp.ok(contentMetadata);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getContent")
    public Rsp<ContentMetadata> getContent(@QueryParam("id") Long id) {
        ContentMetadata contentMetadata = contentService.getContentById(id);
        return Rsp.ok(contentMetadata);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveContent")
    public Rsp saveContent(ContentMetadata contentMetadata) {
        return Rsp.ok(contentService.save(contentMetadata));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateContent")
    public Rsp updateContent(ContentMetadata contentMetadata) {
        return Rsp.ok(contentService.update(contentMetadata));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteContent")
    public Rsp deleteContent(@QueryParam("id") Long id) {
        return Rsp.ok(contentService.delete(id));
    }

}
