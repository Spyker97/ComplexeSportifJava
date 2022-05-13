<?php

namespace App\Repository;

use App\Entity\Region;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Region>
 *
 * @method Region|null find($id, $lockMode = null, $lockVersion = null)
 * @method Region|null findOneBy(array $criteria, array $orderBy = null)
 * @method Region[]    findAll()
 * @method Region[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RegionRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Region::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Region $entity, bool $flush = true): void
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
    public function remove(Region $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    function SearchByEmp($nsc)
    {
        return $this->createQueryBuilder('region')
            ->where ('region.nom = :nom')
            ->setParameter('nom',$nsc)
            ->getQuery()->getResult();


    }
    function SearchNom($nsc)

    {
        return $this->createQueryBuilder('o')
            ->where ('o.nom LIKE :nom_region')
            ->setParameter('nom_region','%'.$nsc.'%')
            ->getQuery()->getResult();



    }
    public function findEntitiesByString($str)
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT p
            FROM App\Entity\Region p
            WHERE p.nom LIKE :str'

        )->setParameter('str', $str);

        // returns an array of Region objects
        return $query->getResult();
    }


    

    // /**
    //  * @return Region[] Returns an array of Region objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Region
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
