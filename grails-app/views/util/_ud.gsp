<input type="hidden" name="id" value="${bean?.id}" />
<div class="buttons">
    <span class="button"><g:actionSubmit class="save" action="Update" value="${message(code:'update', 'default':'Update')}" /></span>
    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'delete.confirm', 'default':'Are you sure?')}');" action="Delete" value="${message(code:'delete', 'default':'Delete')}" /></span>
</div>