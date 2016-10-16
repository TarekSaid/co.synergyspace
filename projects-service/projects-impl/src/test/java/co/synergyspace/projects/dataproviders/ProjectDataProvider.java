package co.synergyspace.projects.dataproviders;

import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 14/10/16.
 */
public class ProjectDataProvider {

    @DataProvider(name = "projects")
    public static Iterator<Object[]> createProjects() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{null});
        params.add(new Object[]{new ArrayList<ProjectEntity>()});
        params.add(new Object[]{Arrays.asList(new ProjectEntity())});
        params.add(new Object[]{Arrays.asList(new ProjectEntity(1L))});
        params.add(new Object[]{Arrays.asList(new ProjectEntity(1L), new ProjectEntity(2L))});

        return params.iterator();
    }

    @DataProvider(name = "businessProjects")
    public static Iterator<Object[]> createBusinessProjects() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{null, null});
        params.add(new Object[]{new BusinessEntity(""), new ArrayList<ProjectEntity>()});
        params.add(new Object[]{new BusinessEntity("Test"), Arrays.asList(new ProjectEntity())});
        params.add(new Object[]{new BusinessEntity("Test"), Arrays.asList(new ProjectEntity(1L))});
        params.add(new Object[]{new BusinessEntity("Hello"),
                Arrays.asList(new ProjectEntity(1L), new ProjectEntity(2L))});

        return params.iterator();
    }

    @DataProvider(name = "projectById")
    public static Iterator<Object[]> createProjectsById() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[] {1L, new ProjectEntity(1L)});
        params.add(new Object[] {2L, new ProjectEntity()});
        params.add(new Object[] {3L, new ProjectEntity(5L)});

        return params.iterator();
    }

    @DataProvider(name = "savedProjects")
    public static Iterator<Object[]> createSavedProjects() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[] {new ProjectEntity(), new ProjectEntity(1L)});
        params.add(new Object[] {new ProjectEntity(2L), new ProjectEntity(2L)});
        params.add(new Object[] {new ProjectEntity(5L), new ProjectEntity()});

        return params.iterator();
    }

    @DataProvider(name = "addProjects")
    public static Iterator<Object[]> createProject() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[] {new ProjectEntity(), new BusinessEntity()});
        params.add(new Object[] {new ProjectEntity(2L), new BusinessEntity("Tarek")});
        params.add(new Object[] {new ProjectEntity(5L), new BusinessEntity("Leticia")});

        return params.iterator();
    }
}