<?php

namespace App\Repository;

use App\Entity\Endroit;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Endroit>
 *
 * @method Endroit|null find($id, $lockMode = null, $lockVersion = null)
 * @method Endroit|null findOneBy(array $criteria, array $orderBy = null)
 * @method Endroit[]    findAll()
 * @method Endroit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class EndroitRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Endroit::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Endroit $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Endroit $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    function SearchByEmp($nsc)
    {
        return $this->createQueryBuilder('endroit')
            ->where ('endroit.nom = :nom')
            ->setParameter('nom',$nsc)
            ->getQuery()->getResult();


    }
    function SearchNom($nsc)

    {
        return $this->createQueryBuilder('o')
            ->where ('o.nom LIKE :nom_endroit')
            ->setParameter('nom_endroit','%'.$nsc.'%')
            ->getQuery()->getResult();



    }
    public function findEntitiesByString($str)
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT p
            FROM App\Entity\Endroit p
            WHERE p.nom LIKE :str'

        )->setParameter('str', $str);

        // returns an array of Endroit objects
        return $query->getResult();
    }

    function reche($data)
    {
        return $this->createQueryBuilder('endroit')
            ->Where('endroit.nom Like :nom')
            ->setParameter('nom', '%'.$data.'%')
            ->getQuery()->getResult();
    }
    function tri_asc()
    {
        return $this->createQueryBuilder('endroit')
            ->orderBy('endroit.numero ','ASC')
            ->getQuery()->getResult();
    }
    function tri_desc()
    {
        return $this->createQueryBuilder('endroit')
            ->orderBy('endroit.numero ','DESC')
            ->getQuery()->getResult();
    }


    // /**
    //  * @return Endroit[] Returns an array of Endroit objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('e.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Endroit
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
