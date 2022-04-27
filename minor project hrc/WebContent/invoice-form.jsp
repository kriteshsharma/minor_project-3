<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

    <title>Landing Page</title>
    <style type="text/css">
  		<%@include file="CSS/landing.css" %>
	</style>
	
    <script type="text/javascript">
    function cancel(relativePath) {
        window.location = relativePath + '/list' ;
    }
    </script>
</head>

<body>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${invoice != null}">
                <form action="update" method="post" class="modal-content">
            </c:if>
            <c:if test="${invoice == null}">
                <form action="insert" method="post" class="modal-content1">
            </c:if>

            <caption class="md_up">
                <h2 class="md_head">
                    <c:if test="${invoice != null}">
                        Edit Invoice
                    </c:if>
                    <c:if test="${invoice == null}">
                        Add New Invoice
                    </c:if>
                </h2>
            </caption>
         
            <c:if test="${invoice != null}">
                <input type="hidden" name="id" value="<c:out value='${invoice.id}' />"/>
            </c:if>

            	<c:if test="${invoice == null}">
            	<div class="md1_up"></div>
                <label class="md1_nm">Customer Name *</label> <input type="text" id="add_inv_name"
                                                value="<c:out value='${invoice.customerName}' />" class="form-control"
                                                name="customerName" required="required">
            
                <label class="md1_dd">Due Date *</label> <input type="date" id="add_inv_dd"
                                                      value="<c:out value='${invoice.dueDateAsString}' />" class="form-control"
                                                      name="dueDate" required="required">
            
                <label class="md1_cn">Customer No. *</label> <input type="text" id="add_inv_cn"
                                                 value="<c:out value='${invoice.customerID}' />" class="form-control"
                                                 name="customerID" required="required">
            
                <label class="md1_nts">Notes</label> <textarea name="notes" id="add_inv_nts" value="<c:out value='${invoice.notes}'/>"></textarea>
            		<br>
                <label class="md1_ino">Invoice No. *</label> <input type="text" id="add_inv_ino"
                                                     value="<c:out value='${invoice.invoiceID}' />" class="form-control"
                                                     name="invoiceID" required="required">
            

            		<br>
                <label class="md1_amo">Invoice Amount *</label> <input type="text" id="add_inv_amt"
                                                    value="<c:out value='${invoice.amount}' />" class="form-control"
                                                    name="amount" required="required">
            

				<div class="md1_dn">
					<button type="submit" class="btn btn-success" id="add_add_btn">Add</button>

                	<button onclick="window.location.href='<%=request.getContextPath()%>/list'" id="add_cncl_btn" class="btn btn-success">Cancel
                </button>
				</div>	
            	</c:if>	
            
                <c:if test="${invoice != null}">
                	<div class="md1_up"></div>
                	<label class="md_amt">Invoice Amount</label> <input type="text" id="edt_inv_amt"
                                                    value="<c:out value='${invoice.amount}' />" class="form-control"
                                                    name="amount" required="required">
                      <br>
                    <label class="md_nts">Notes</label> <textarea name="notes" id="edt_inv_nts" value="<c:out value='${invoice.notes}'/>"></textarea>
                    <div class="md_dn">
                    	<button type="submit" class="btn btn-success" id="add_add_btn">Edit</button>

                		<button onclick="window.location.href='<%=request.getContextPath()%>/list'" id="add_cncl_btn" class="btn btn-success">Cancel
                		</button>
                    </div>
                </c:if>
                
               </form>

        </div>
    </div>
</div>
</body>
</html>
