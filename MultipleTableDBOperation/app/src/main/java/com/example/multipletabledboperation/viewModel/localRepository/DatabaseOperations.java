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

    public void addProduct(final InsertOperation insertOperation, final String s){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Products product = new Products();
                product.setProdName(s);
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

    public void getAllProduct(final SendProductData sendProductData){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Products> list = DatabaseClient.getInstance(context).getDatabase().productDao().getAllProducts();
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProductData.setLiveData(list);
                    }
                });
            }
        });
    }

    public void addProject(final SendProjectData sendProjectData, final String s){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Projects project = new Projects();
                project.setProName(s);
                DatabaseClient.getInstance(context).getDatabase().projectDao().insertProjects(project);


                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        getAllProjects(sendProjectData);
                    }
                });

            }
        });

    }

    public void getAllProjects(final SendProjectData sendProjectData){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Projects> list = DatabaseClient.getInstance(context).getDatabase().projectDao().getAllProjects();
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProjectData.setLiveData(list);
                    }
                });
            }
        });
    }

    public void addTechnology(final SendTechnologyData sendTechnologyData, final String s){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Technology technology = new Technology();
                technology.setTechName(s);
                DatabaseClient.getInstance(context).getDatabase().technologyDao().insertTechnology(technology);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        getAllTechnology(sendTechnologyData);
                    }
                });

            }
        });

    }

    public void getAllTechnology(final SendTechnologyData sendTechnologyData){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Technology> list = DatabaseClient.getInstance(context).getDatabase().technologyDao().getAllTechnology();
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendTechnologyData.setLiveData(list);
                    }
                });
            }
        });
    }

    public void addDeveloper(final SendDeveloperData sendDeveloperData, final String s){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Developer developer = new Developer();
                developer.setDevNAme(s);
                developer.setDevAddress(s);
                developer.setDevMobile(1);

               DatabaseClient.getInstance(context).getDatabase().developerDao().insertDeveloper(developer);

                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        getAllDeveloper(sendDeveloperData);
                    }
                });

            }
        });

    }

    public void getAllDeveloper(final SendDeveloperData sendDeveloperData){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Developer> list = DatabaseClient.getInstance(context).getDatabase().developerDao().getAllDeveloper();
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendDeveloperData.setLiveData(list);
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

    public void getProductId(final SendProdId sendProdId, final String prodName){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final int prodId = DatabaseClient.getInstance(context)
                        .getDatabase().companyProductJoinDao().getProdId(prodName);
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendProdId.prodId(prodId);
                    }
                });
            }
        });
    }

    public interface SendProdId{
        void prodId(int id);
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
