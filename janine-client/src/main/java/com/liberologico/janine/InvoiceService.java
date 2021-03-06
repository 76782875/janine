package com.liberologico.janine;

import com.liberologico.janine.entities.Invoice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface InvoiceService
{
    @POST( "/{prefix}/validate" )
    Call<Invoice> validate( @Path( "prefix" ) String prefix, @Body Invoice invoice );

    @POST( "/{prefix}/download" )
    Call<ResponseBody> generate( @Path( "prefix" ) String prefix, @Body Invoice invoice );

    @POST( "/{prefix}/{id}/download" )
    Call<ResponseBody> generate( @Path( "prefix" ) String prefix, @Path( "id" ) Long id, @Body Invoice invoice );

    @POST( "/{prefix}" )
    Call<Long> generateAndUpload( @Path( "prefix" ) String prefix, @Body Invoice invoice );

    @POST( "/{prefix}/{id}" )
    Call<Long> generateAndUpload( @Path( "prefix" ) String prefix, @Path( "id" ) Long id, @Body Invoice invoice );

    @POST( "/{prefix}/{id}?regenerate=true" )
    Call<Long> regenerate( @Path( "prefix" ) String prefix, @Path( "id" ) Long id, @Body Invoice invoice );

    @GET( "/{prefix}/{id}.pdf" )
    Call<ResponseBody> downloadPdf( @Path( "prefix" ) String prefix, @Path( "id" ) Long id );

    @GET( "/{prefix}/{id}.json" )
    Call<ResponseBody> downloadJson( @Path( "prefix" ) String prefix, @Path( "id" ) Long id );

    @GET( "/fields" )
    Call<List<String>> getFields();

    @GET( "/schema" )
    Call<Object> getInvoiceSchema();
}
