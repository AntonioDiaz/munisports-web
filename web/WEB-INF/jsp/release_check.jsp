<%@include file="taglibs.jsp" %>
<script>
    $(document).ready(function () {});

    function fCheckRelease() {
        switchLoading(true);
        peticionJqueryAjax("check_release_ajax", {});
    }

    function fBtnTask(){
        switchLoading(true);
        peticionJqueryAjax("enqueueTask", {});
    }

    function peticionJqueryAjax (url, params) {
        $.ajax({
            dataType: "text",
            url: url,
            data: params
        }).done((data, textStatus, jqXHR) => {
            showDialogAlert("Resultado de la acci�n: " + data, ()=>{
                window.location.href = "/releases/check";
            });
            switchLoading(false);
        }).fail((jqXHR, textStatus, errorThrown) => {
            switchLoading(false);
            showDialogAlert("error resultado: " + textStatus);
        });
    }


</script>
<div id="contentDiv">
    <div style="text-align: right">
        <button type="button" class="btn btn-success" onclick="fCheckRelease()">Comprobar actualizaci�n</button>
    </div>
    <hr>
    <c:if test="${last_release!=null}">
        <div class="row result_list">
            <div class="col-sm-10">
                    ${last_release}
            </div>
            <div class="col-sm-2">
                <c:if test="${last_release.taskStart==null}">
                    <button id="btnDelete" type="button" class="btn btn-primary" onclick="fBtnTask()">
                        <span class="glyphicon glyphicon-tasks"></span>
                    </button>
                </c:if>
                <c:if test="${last_release.taskEnd != null}">
                    <div class="bg-success text-white">Tarea finalizada</div>
                </c:if>
                <c:if test="${last_release.taskStart!=null && last_release.taskEnd == null}">
                    <div class="bg-warning text-white">Tarea en proceso.</div>
                </c:if>
            </div>
        </div>
    </c:if>
    <c:if test="${last_release==null}">
        No hay last_release
    </c:if>
</div>