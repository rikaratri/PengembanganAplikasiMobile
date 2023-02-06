package edu.uksw.fti.pam.pam_activity_intent


sealed class Navigation (var route: String, var icon: Int, var title: String)
{
    object Beranda : Navigation("home", R.drawable.ic_home, "Beranda")
    object Pesanan : Navigation("pesanan", R.drawable.ic_list, "Pesanan")
    object Chat : Navigation("chat", R.drawable.ic_chat, "Chat")
    object MyAccount : Navigation("myaccount", R.drawable.ic_account, "MyAccount")
    object Notification : Navigation("notification", R.drawable.ic_notifications, "")
    object Border : Navigation("border", R.drawable.ic_border, "")
    object Logo : Navigation("logo", R.drawable.logo, "")
}
