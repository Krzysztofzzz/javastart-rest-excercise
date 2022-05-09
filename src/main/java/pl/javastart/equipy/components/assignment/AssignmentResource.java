package pl.javastart.equipy.components.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.components.exceptions.InvalidAssignmentException;

import java.net.URI;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentResource {

    private AssignmentService assignmentService;

    public AssignmentResource(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("")
    public ResponseEntity<AssignmentDto> saveAssignment(@RequestBody AssignmentDto assignmentDto) {
        AssignmentDto savedAssignment;
        try {
            savedAssignment = assignmentService.createAssignment(assignmentDto);
        } catch (InvalidAssignmentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAssignment.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedAssignment);
    }

}