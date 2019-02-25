package com.example.multipletabledboperation.viewModel.localRepository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.multipletabledboperation.service.model.Company;
import com.example.multipletabledboperation.service.model.Company_Product;
import com.example.multipletabledboperation.service.model.Developer;
import com.example.multipletabledboperation.service.model.Product_Project;
import com.example.multipletabledboperation.service.model.Products;
import com.example.multipletabledboperation.service.model.Project_Technology;
import com.example.multipletabledboperation.service.model.Projects;
import com.example.multipletabledboperation.service.model.Technology;
import com.example.multipletabledboperation.service.model.Technology_Developer;
import com.example.multipletabledboperation.service.room.AppExecutor;
import com.example.multipletabledboperation.service.room.DatabaseClient;

import java.util.List;

public class DatabaseOperations {
    private Context context;

    public DatabaseOperations(Context context){
        this.context = context;
    }

    public void addCompany(final InsertOperation insertOperation, final String s){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Company company = new Company();
                company.setCompName(s);
                final long id = DatabaseClient.getInstance(context).getDatabase().companyDao().insertCompany(company);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete(id);
                    }
                });

            }
        });

    }

    public void getAllCompanies(final SendCompanyData sendCompanyData){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
              final List<Company> list = DatabaseClient.getInstance(context).getDatabase().companyDao().getAllCompany();
               AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                   @Override
                   public void run() {
                       sendCompanyData.setLiveData(list);
                   }
               });
            }
        });
    }

    public void addProduct(final InsertOperation insertOperation, final String productName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Products product = new Products();
                product.setProdName(productName);
               final long id = DatabaseClient.getInstance(context).getDatabase().productDao().insertProducts(product);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete(id);
                    }
                });

            }
        });

    }

    public void addProject(final InsertOperation insertOperation, final String projectName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Projects project = new Projects();
                project.setProName(projectName);
               final long id = DatabaseClient.getInstance(context).getDatabase().projectDao().insertProjects(project);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete(id);
                    }
                });

            }
        });

    }

    public void addTechnology(final InsertOperation insertOperation, final String technologyName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Technology technology = new Technology();
                technology.setTechName(technologyName);
                final long id = DatabaseClient.getInstance(context).getDatabase().technologyDao().insertTechnology(technology);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete(id);
                    }
                });

            }
        });

    }

    public void addDeveloper(final InsertOperation insertOperation, final String devName, final String devAdd, final int devMob){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Developer developer = new Developer();
                developer.setDevNAme(devName);
                developer.setDevAddress(devAdd);
                developer.setDevMobile(devMob);

              final long id = DatabaseClient.getInstance(context).getDatabase().developerDao().insertDeveloper(developer);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                       insertOperation.insertComplete(id);
                    }
                });

            }
        });

    }

    public void addCompany_Product(final InsertOperation insertOperation, final int compId, final int prodId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Company_Product company_product = new Company_Product();
                company_product.setCompanyId(compId);
                company_product.setProductId(prodId);
                final long id = DatabaseClient.getInstance(context).getDatabase().companyProductJoinDao().insertCD(company_product);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete1(id);
                    }
                });

            }
        });

    }

    public void getAllProductForCompany(final SendCompany_ProductData sendCompany_productData, final Integer compId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Products> list = DatabaseClient.getInstance(context)
                        .getDatabase().companyProductJoinDao().getProdForComp(compId);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendCompany_productData.setLiveDataProducts(list);
                    }
                });
            }
        });
    }

    public void getProductId(final SendId sendProdId, final String prodName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final int prodId = DatabaseClient.getInstance(context)
                        .getDatabase().companyProductJoinDao().getProdId(prodName);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProdId.getId(prodId);
                    }
                });
            }
        });
    }


    public void insertProduct_Project(final InsertOperation insertOperation, final int productId, final int projectId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Product_Project product_project = new Product_Project();
                product_project.setProductId(productId);
                product_project.setProjectId(projectId);

                final long id = DatabaseClient.getInstance(context).getDatabase()
                        .productProjectJoinDao().insertPP(product_project);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete1(id);
                    }
                });
            }
        });
    }

    public void getAllProjectsForProducts(final SendProduct_ProjectData sendProduct_projectData, final Integer prodId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Projects> projectsList = DatabaseClient.getInstance(context).getDatabase()
                        .productProjectJoinDao().getProjForProd(prodId);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProduct_projectData.setLiveDataProjects(projectsList);
                    }
                });
            }
        });
    }

    public void getProjectId(final SendId sendProjId, final String prjectName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final int projectId = DatabaseClient.getInstance(context).getDatabase().productProjectJoinDao()
                        .getProjectId(prjectName);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProjId.getId(projectId);
                    }
                });
            }
        });
    }

    public void insertProject_Technology(final InsertOperation insertOperation, final int projectId, final int technologyId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Project_Technology project_technology = new Project_Technology();
                project_technology.setProjectId(projectId);
                project_technology.setTechnologyId(technologyId);

                final long id = DatabaseClient.getInstance(context).getDatabase()
                        .projectTechnologyJoinDao().insertPT(project_technology);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete1(id);
                    }
                });
            }
        });
    }


    public void getAllTechnologyForProjects(final SendProject_TechnologyData sendProject_technologyData, final Integer projectId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Technology> technologyList = DatabaseClient.getInstance(context).getDatabase()
                        .projectTechnologyJoinDao().getTechForProj(projectId);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProject_technologyData.setLiveDataTechnology(technologyList);
                    }
                });
            }
        });
    }

    public void getTechnologyId(final SendId sendTechId, final String technologyName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final int techId = DatabaseClient.getInstance(context).getDatabase()
                        .projectTechnologyJoinDao().getTechnologyId(technologyName);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendTechId.getId(techId);
                    }
                });
            }
        });
    }

    public void insertTechnology_Developer(final InsertOperation insertOperation, final int technologyId, final int developerId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
               Technology_Developer project_technology =new Technology_Developer();
               project_technology.setDeveloperId(developerId);
               project_technology.setTechnologyId(technologyId);

                final long id = DatabaseClient.getInstance(context).getDatabase()
                        .technologyDeveloperJoinDao().insertTD(project_technology);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertOperation.insertComplete1(id);
                    }
                });
            }
        });
    }


    public void getAllDeveloperForTechnology(final SendTechnology_DeveloperData sendTechnology_developerData, final Integer technologyId){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Developer> developerList = DatabaseClient.getInstance(context).getDatabase()
                        .technologyDeveloperJoinDao().getDevForTech(technologyId);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendTechnology_developerData.setLiveDataDeveloper(developerList);
                    }
                });
            }
        });
    }

    public void getDeveloperId(final SendId sendDevId, final String developerName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final int devId = DatabaseClient.getInstance(context).getDatabase()
                        .technologyDeveloperJoinDao().getDeveloperId(developerName);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendDevId.getId(devId);
                    }
                });
            }
        });
    }

    public interface SendId{
        void getId(int id);
    }
    public interface SendCompanyData{
        void setLiveData(List<Company> liveData);
    }
    public interface InsertOperation{
        void insertComplete(long id);
        void insertComplete1(long id);
    }
    public interface SendProductData{
        void setLiveData(List<Products> liveData);
    }
    public interface SendProjectData{
        void setLiveData(List<Projects> liveData);
    }
    public interface SendTechnologyData{
        void setLiveData(List<Technology> liveData);
    }
    public interface SendDeveloperData{
        void setLiveData(List<Developer> liveData);
    }
    public interface SendCompany_ProductData{
        void setLiveDataCompany(List<Company> liveData);
        void setLiveDataProducts(List<Products> liveData);
    }
    public interface SendProduct_ProjectData{
        void setLiveDataProducts(List<Products> liveData);
        void setLiveDataProjects(List<Projects> liveData);
    }
    public interface SendProject_TechnologyData{
        void setLiveDataProjects(List<Projects> liveData);
        void setLiveDataTechnology(List<Technology> liveData);
    }
    public interface SendTechnology_DeveloperData{
        void setLiveDataTechnology(List<Technology> liveData);
        void setLiveDataDeveloper(List<Developer> liveData);
    }


}
