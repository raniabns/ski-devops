import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.services.CourseServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CourseServicesImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServicesImpl courseServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllCourses() {
        // Créer une liste fictive de courses
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(3, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 1));
        courses.add(new Course(2, TypeCourse.COLLECTIVE_CHILDREN, Support.SNOWBOARD, 75.0f, 2));

        // Définir le comportement du repository mocké
        when(courseRepository.findAll()).thenReturn(courses);

        // Appeler la méthode du service
        List<Course> retrievedCourses = courseServices.retrieveAllCourses();

        // Vérifier que la méthode du repository a été appelée une fois
        verify(courseRepository, times(1)).findAll();

        // Vérifier que la liste récupérée correspond à la liste fictive
        Assertions.assertEquals(courses, retrievedCourses);
    }

    @Test
    public void testAddCourse() {
        // Créer une course fictive
        Course course = new Course(3, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 1);

        // Définir le comportement du repository mocké
        when(courseRepository.save(course)).thenReturn(course);

        // Appeler la méthode du service
        Course addedCourse = courseServices.addCourse(course);

        // Vérifier que la méthode du repository a été appelée une fois
        verify(courseRepository, times(1)).save(course);

        // Vérifier que la course retournée correspond à la course fictive
        Assertions.assertEquals(course, addedCourse);
    }

    @Test
    public void testUpdateCourse() {
        // Créer une course fictive
        Course course = new Course(3, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 1);

        // Définir le comportement du repository mocké
        when(courseRepository.save(course)).thenReturn(course);

        // Appeler la méthode du service
        Course updatedCourse = courseServices.updateCourse(course);

        // Vérifier que la méthode du repository a été appelée une fois
        verify(courseRepository, times(1)).save(course);

        // Vérifier que la course retournée correspond à la course fictive
        Assertions.assertEquals(course, updatedCourse);
    }

    @Test
    public void testRetrieveCourse() {
        // Créer une course fictive
        Course course = new Course(3, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 1);

        // Définir le comportement du repository mocké
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Appeler la méthode du service
        Course retrievedCourse = courseServices.retrieveCourse(1L);

        // Vérifier que la méthode du repository a été appelée une fois avec l'ID spécifié
        verify(courseRepository, times(1)).findById(1L);

        // Vérifier que la course retournée correspond à la course fictive
        Assertions.assertEquals(course, retrievedCourse);
    }
}
