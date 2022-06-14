package at.htlleonding.logic.xmlDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("WorksOfAuthors")
public class WorksOfAuthorsDTO {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Author")
    List<at.htlleonding.logic.xmlDTOs.AuthorDTO> authors = new ArrayList<at.htlleonding.logic.xmlDTOs.AuthorDTO>();

    public WorksOfAuthorsDTO() {
    }

    public List<at.htlleonding.logic.xmlDTOs.AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthor(List<at.htlleonding.logic.xmlDTOs.AuthorDTO> authors) {
        this.authors = authors;
    }
}
