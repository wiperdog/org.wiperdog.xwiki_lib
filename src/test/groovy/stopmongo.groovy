def scriptFile = getClass().protectionDomain.codeSource.location.path
def scriptDir = new File(scriptFile).parent

def mongo = '/home/ora112d/mongodb/bin/mongo'

def eval = " --eval 'db.getSisterDB(\"admin\").shutdownServer();'"
def otherargs = Args.clientargs(args)
def shutdowncmd = ""
if (System.getProperty("os.name").contains('Win')) {
	shutdowncmd = mongo + eval + otherargs
} else {
	shutdowncmd = "net stop mongodb"
}
println shutdowncmd

def proc = shutdowncmd.execute()
proc.waitFor()
println proc.text
