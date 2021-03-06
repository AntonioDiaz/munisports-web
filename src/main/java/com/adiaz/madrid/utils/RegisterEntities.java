package com.adiaz.madrid.utils;

import com.adiaz.madrid.daos.GroupDAO;
import com.adiaz.madrid.daos.UsersDAO;
import com.adiaz.madrid.entities.*;
import com.adiaz.madrid.services.ParametersManager;
import com.adiaz.madrid.services.UsersManager;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.QueryKeys;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class RegisterEntities {

    private static final Logger logger = Logger.getLogger(RegisterEntities.class);

    @Autowired
    GroupDAO groupDAO;

    @Autowired
    UsersManager usersManager;

    @Autowired
    ParametersManager parametersManager;

    public void init() throws Exception {


        ObjectifyService.register(Release.class);
        ObjectifyService.register(User.class);
        ObjectifyService.register(Team.class);
        ObjectifyService.register(Place.class);
        ObjectifyService.register(Group.class);
        ObjectifyService.register(Match.class);
        ObjectifyService.register(ClassificationEntry.class);
        ObjectifyService.register(Parameter.class);
        ObjectifyService.register(Notification.class);

        /* clean DB. */
        /*try {
            List<Key<Release>> listRelease = ofy().load().type(Release.class).keys().list();
            ofy().delete().keys(listRelease);
            List<Key<Group>> listGroups = ofy().load().type(Group.class).keys().list();
            ofy().delete().keys(listGroups);
            List<Key<Team>> listTeam = ofy().load().type(Team.class).keys().list();
            ofy().delete().keys(listTeam);
            List<Key<Place>> placesList = ofy().load().type(Place.class).keys().list();
            ofy().delete().keys(placesList);
            QueryKeys<Match> matchesList = ofy().load().type(Match.class).limit(5000).keys();
            while (matchesList.list().size() != 0) {
                ofy().delete().keys(matchesList).now();
                logger.debug("delete redords: " + matchesList.list().size());
                matchesList = ofy().load().type(Match.class).limit(5000).keys();
            }
            matchesList = ofy().load().type(Match.class).keys();
            ofy().delete().keys(matchesList).now();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ofy().clear();
        }*/
        /* insert parameters */
        if (parametersManager.queryByKey(DeportesMadridConstants.PARAMETER_DEBUG)==null) {
            Parameter parameter = new Parameter();
            parameter.setKey(DeportesMadridConstants.PARAMETER_DEBUG);
            parameter.setValue("false");
            parametersManager.add(parameter);
        }
        if (parametersManager.queryByKey(DeportesMadridConstants.PARAMETER_FCM_SERVER_KEY)==null) {
            Parameter parameter = new Parameter();
            parameter.setKey(DeportesMadridConstants.PARAMETER_FCM_SERVER_KEY);
            parameter.setValue("NOT_VALID");
            parametersManager.add(parameter);
        }
        if (parametersManager.queryByKey(DeportesMadridConstants.PARAMETER_URL_CLASSIFICATION)==null) {
            Parameter parameter = new Parameter();
            parameter.setKey(DeportesMadridConstants.PARAMETER_URL_CLASSIFICATION);
            parameter.setValue(DeportesMadridConstants.URL_CLASSIFICATION);
            parametersManager.add(parameter);
        }
        if (parametersManager.queryByKey(DeportesMadridConstants.PARAMETER_URL_MATCHES)==null) {
            Parameter parameter = new Parameter();
            parameter.setKey(DeportesMadridConstants.PARAMETER_URL_MATCHES);
            parameter.setValue(DeportesMadridConstants.URL_MATCHES);
            parametersManager.add(parameter);
        }
        createTestUser();
        logger.debug("init DataBase finished");
    }
    private void createTestUser() {
        try {
            logger.info("Creating test user.");
            if (usersManager.queryAllUsers().isEmpty()) {
                User user = new User("adiaz", "ab7e756f02822a9c5042240fecbb9af076e20b892b7c0749be7ab712d82b363f", true, true, false, true);
                usersManager.addUser(user);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}