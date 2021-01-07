
package com.adc.da.pc_trial_problem.webServiceClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reviewProblemByZGCheckReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reviewProblemByZGCheckReturn"
})
@XmlRootElement(name = "reviewProblemByZGCheckResponse")
public class ReviewProblemByZGCheckResponse {

    @XmlElement(required = true)
    protected String reviewProblemByZGCheckReturn;

    /**
     * 获取reviewProblemByZGCheckReturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewProblemByZGCheckReturn() {
        return reviewProblemByZGCheckReturn;
    }

    /**
     * 设置reviewProblemByZGCheckReturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewProblemByZGCheckReturn(String value) {
        this.reviewProblemByZGCheckReturn = value;
    }

}
