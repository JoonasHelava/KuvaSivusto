
package wad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joonas
 */

//Test to check the basic functions of site
@RunWith(SpringRunner.class)
@SpringBootTest
public class KuvaSivustoTest {
    
    private final String API_URI_SELAA = "/pictures";
    private final String API_URI_LISAA = "/addpicture";
    private final String API_URI_AANESTA_PLUS = "/voteplus/1";
    private final String API_URI_AANESTA_MINUS = "/voteminus/1";
    private final String API_URI_SINGLE_SELAA = "/pictures/1";
    private final String API_URI_SINGLE_KOMMENTOI = "/comments/1";
    
    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    @Test
    public void redirectedFromPictures() throws Exception {
        mockMvc.perform(get(API_URI_SELAA))
                .andExpect(redirectedUrl(API_URI_SINGLE_SELAA));
    }
    @Test
    public void canPostPicture() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "kuha.png", "image/png", "kuva".getBytes());
        mockMvc.perform(multipart(API_URI_SELAA).file(multipartFile))
                .andExpect(redirectedUrl(API_URI_SELAA));
    }
    @Test
    public void canUpvote() throws Exception {
        this.postPicture();
        mockMvc.perform(get(API_URI_AANESTA_PLUS))
                .andExpect(redirectedUrl(API_URI_SELAA));
    }
    @Test
    public void canDownvote() throws Exception {
        this.postPicture();
        mockMvc.perform(get(API_URI_AANESTA_MINUS))
                .andExpect(redirectedUrl(API_URI_SELAA));
    }
    @Test
    public void canPostCommentsAndContainsIt() throws Exception {
        this.postPicture();
        mockMvc.perform(
                post(API_URI_SINGLE_KOMMENTOI).param("title", "Test").param("content", "Text")
        ).andExpect(redirectedUrl(API_URI_SELAA));
        MvcResult res = mockMvc.perform(get(API_URI_SINGLE_SELAA))
                .andReturn();
        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("Test"));
    }
    public void postPicture() throws Exception{
        MockMultipartFile multipartFile = new MockMultipartFile("file", "kuha.png", "image/png", "kuva".getBytes());
        mockMvc.perform(multipart(API_URI_SELAA).file(multipartFile)).andReturn();
    }
}
