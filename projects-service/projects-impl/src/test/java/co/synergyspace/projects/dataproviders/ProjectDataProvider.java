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
}
