<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:variable name="context" select="dto/context"/>
  <xsl:variable name="rootUrl" select="dto/rootUrl"/>
  <xsl:variable name="synchToken" select="dto/synchronizationToken"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Simple Registrator</title>
      </head>
      <body>
        <h1>Simple Registrator Application</h1>
        <br/>
        <br/>
        <p/>
        <form action="{$context}{$rootUrl}" method="post" name="actionForm">
          <input type="hidden" name="actionName"/>
          <input type="hidden" name="synchToken" value="{$synchToken}"/>
          <a href="javascript:setActionName('new_contact')">Create a new Contact</a>
          <p/>
          <a href="javascript:setActionName('new_user')">Create a new User</a>
          <p/>
          <a href="javascript:setActionName('view_contacts')">View existing Contacts</a>
          <p/>
          <a href="javascript:setActionName('view_users')">View existing Users</a>
        </form>
      </body>
      <script language="JavaScript" type="text/javascript">
        function setActionName(actionName)
        {
        document.actionForm.actionName.value = actionName;
        document.actionForm.submit();
        }
      </script>
    </html>
  </xsl:template>
</xsl:stylesheet>