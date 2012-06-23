/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcbd.tampagov.citysites.model.CitySite;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
public interface CitySiteMapper {

    @Select("SELECT * citysites WHERE id = #{id}")
    CitySite getCitySite(@Param("id") String id);
    
}
